package es.jcescudero15.mygithubapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import es.jcescudero15.mygithubapp.R;
import es.jcescudero15.mygithubapp.ui.contract.RepositoryDetailsContract;
import es.jcescudero15.mygithubapp.ui.viewmodel.RepositoryViewModel;

public class RepositoryDetailsActivity extends DaggerAppCompatActivity implements RepositoryDetailsContract.View {

    private final static String REPOSITORY = "REPOSITORY";


    @BindView(R.id.repository_details_image)
    ImageView mRepositoryImage;

    @BindView(R.id.repository_details_name)
    TextView mRepositoryName;

    @BindView(R.id.repository_details_url)
    TextView mRepositoryUrl;

    @BindView(R.id.repository_details_viewers)
    TextView mRepositoryViewers;

    @BindView(R.id.repository_details_stars)
    TextView mRepositoryStars;

    @BindView(R.id.repository_details_forks)
    TextView mRepositoryForks;

    @BindView(R.id.repository_details_language)
    TextView mRepositoryLanguage;

    @BindView(R.id.repository_details_description)
    TextView mRepositoryDescription;

    @BindView(R.id.repository_details_progress)
    ProgressBar mProgress;


    @Inject
    RepositoryDetailsContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        ButterKnife.bind(this);

        mPresenter.takeView(this);

        getRepositoryDetails();
    }

    @Override
    protected void onDestroy() {
        mPresenter.dropView();
        super.onDestroy();
    }

    @Override
    public void showRepositoryDetails(RepositoryViewModel repository) {
        Glide.with(this)
                .load(repository.getImageUrl())
                .into(mRepositoryImage);

        mRepositoryName.setText(String.format("%s/%s", repository.getOwnerName(), repository.getName()));
        mRepositoryUrl.setText(String.format(" %s", repository.getGitHubUrl()));
        mRepositoryViewers.setText(String.valueOf(repository.getObserversCount()));
        mRepositoryStars.setText(String.valueOf(repository.getStarsCount()));
        mRepositoryForks.setText(String.valueOf(repository.getForksCount()));
        mRepositoryLanguage.setText(String.format(" %s", repository.getLanguage()));
        mRepositoryDescription.setText(repository.getDescription());
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, getString(R.string.repository_details_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }


    private void getRepositoryDetails() {
        try {
            Bundle extras = getIntent().getExtras();
            RepositoryViewModel repository = (RepositoryViewModel) extras.get(REPOSITORY);
            mPresenter.getRepositoryDetails(repository.getOwnerName(), repository.getName());

        } catch (NullPointerException e) {
            showErrorMessage();
        }
    }
}
