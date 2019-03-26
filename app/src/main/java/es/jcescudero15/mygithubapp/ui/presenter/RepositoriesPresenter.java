package es.jcescudero15.mygithubapp.ui.presenter;

import java.util.List;

import javax.inject.Inject;

import es.jcescudero15.mygithubapp.domain.model.Repository;
import es.jcescudero15.mygithubapp.domain.usecase.GetPublicRepositories;
import es.jcescudero15.mygithubapp.domain.usecase.SearchPublicRepositories;
import es.jcescudero15.mygithubapp.ui.contract.RepositoriesContract;
import es.jcescudero15.mygithubapp.ui.mapper.ViewModelMapper;
import es.jcescudero15.mygithubapp.utils.di.scopes.ActivityScoped;
import io.reactivex.observers.DisposableObserver;

@ActivityScoped
public class RepositoriesPresenter implements RepositoriesContract.Presenter {

    private RepositoriesContract.View mView;
    private GetPublicRepositories mGetPublicRepositories;
    private SearchPublicRepositories mSearchPublicRepositories;


    @Inject
    RepositoriesPresenter(GetPublicRepositories getPublicRepositories, SearchPublicRepositories
            searchPublicRepositories) {
        mGetPublicRepositories = getPublicRepositories;
        mSearchPublicRepositories = searchPublicRepositories;
    }


    @Override
    public void getPublicRepositories(Integer startId) {
        mView.showProgressLoading();
        mGetPublicRepositories.execute(new DisposableObserver<List<Repository>>() {
            @Override
            public void onNext(List<Repository> repositories) {
                mView.hideProgressLoading();
                mView.showRepositories(ViewModelMapper.transform(repositories));
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgressLoading();
            }

            @Override
            public void onComplete() {
            }
        }, new GetPublicRepositories.Params(startId));
    }

    @Override
    public void searchPublicRepositories(String query, Integer page) {
        mView.showProgressLoading();
        mSearchPublicRepositories.execute(new DisposableObserver<List<Repository>>() {
            @Override
            public void onNext(List<Repository> repositories) {
                mView.hideProgressLoading();

                if (repositories.size() == 0) {
                    mView.setListLoaded();
                    return;
                }

                mView.showRepositories(ViewModelMapper.transform(repositories));
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgressLoading();
            }

            @Override
            public void onComplete() {

            }
        }, new SearchPublicRepositories.Params(query, page));
    }


    @Override
    public void takeView(RepositoriesContract.View view) {
        mView = view;
        getPublicRepositories(0);
    }

    @Override
    public void dropView() {
        mGetPublicRepositories.dispose();
        mSearchPublicRepositories.dispose();
        mView = null;
    }
}
