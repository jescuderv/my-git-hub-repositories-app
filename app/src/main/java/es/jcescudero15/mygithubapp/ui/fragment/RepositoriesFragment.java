package es.jcescudero15.mygithubapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import es.jcescudero15.mygithubapp.R;
import es.jcescudero15.mygithubapp.ui.activity.RepositoryDetailsActivity;
import es.jcescudero15.mygithubapp.ui.adapter.RepositoriesAdapter;
import es.jcescudero15.mygithubapp.ui.contract.RepositoriesContract;
import es.jcescudero15.mygithubapp.ui.presenter.RepositoriesPresenter;
import es.jcescudero15.mygithubapp.ui.viewmodel.RepositoryViewModel;

public class RepositoriesFragment extends DaggerFragment implements RepositoriesContract.View {

    private final static String REPOSITORY = "REPOSITORY";


    @BindView(R.id.repositories_search_view)
    SearchView mSearchView;

    @BindView(R.id.repositories_list)
    RecyclerView mRepositoryList;

    @BindView(R.id.repositories_list_progress)
    ProgressBar mListProgress;


    /* Handle pagination*/
    private RepositoriesAdapter mRepositoriesAdapter;
    private final int VISIBLE_THRESHOLD = 1;
    private Integer mLastRepositoryLoaded = 0; // Handle all repositories pagination
    private Integer mSearchPage = 1; // Handle search pagination
    private boolean mIsSearching = false;
    private String mQuery = "";
    private boolean mIsLoading = false;
    private boolean mIsListLoaded = false;


    @Inject
    RepositoriesPresenter mPresenter;


    @Inject
    public RepositoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repositories, container, false);
        ButterKnife.bind(this, view);

        mPresenter.takeView(this);

        setUpRecyclerView();
        setUpSearchView();

        return view;
    }


    @Override
    public void onDestroyView() {
        mPresenter.dropView();
        super.onDestroyView();
    }


    @Override
    public void showProgressLoading() {
        mIsLoading = true;
        mListProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressLoading() {
        mIsLoading = false;
        mListProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRepositories(List<RepositoryViewModel> repositories) {
        mRepositoriesAdapter.addData(repositories);

        if (!mIsSearching && repositories.size() > 0) // Save last item id to get more with pagination
            mLastRepositoryLoaded = repositories.get(repositories.size() - 1).getId();
    }


    @Override
    public void setListLoaded() {
        mIsListLoaded = true;
    }


    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRepositoryList.setLayoutManager(layoutManager);

        mRepositoriesAdapter = new RepositoriesAdapter((repository, profileImage) -> {
            Intent intent = new Intent(getActivity(), RepositoryDetailsActivity.class);
            intent.putExtra(REPOSITORY, repository);
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), profileImage,
                    getResources().getString(R.string.trans_profile_image_users)).toBundle());

        }, getActivity());
        mRepositoryList.setAdapter(mRepositoriesAdapter);

        mRepositoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Integer totalItemCount = layoutManager.getItemCount();
                Integer lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!mIsLoading && !mIsListLoaded && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    mIsLoading = true;

                    if (mIsSearching) { // Handle if searching or not
                        mSearchPage++;
                        mPresenter.searchPublicRepositories(mQuery, mSearchPage);
                    } else
                        mPresenter.getPublicRepositories(mLastRepositoryLoaded);
                }

            }
        });
    }

    private void setUpSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // Clear data set and reset pagination values
                mRepositoriesAdapter.clearData();
                mSearchPage = 1;
                mLastRepositoryLoaded = 0;
                mIsListLoaded = false;

                if (query.trim().isEmpty()) { // Reset values
                    mIsSearching = false;
                    mQuery = "";

                    mPresenter.getPublicRepositories(mLastRepositoryLoaded);

                } else {
                    mIsSearching = true;
                    mQuery = query;

                    mPresenter.searchPublicRepositories(query, mSearchPage);
                }

                return false;
            }
        });
    }
}
