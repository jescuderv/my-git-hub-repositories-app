package es.jcescudero15.mygithubapp.ui.presenter;

import javax.inject.Inject;

import es.jcescudero15.mygithubapp.domain.model.Repository;
import es.jcescudero15.mygithubapp.domain.usecase.GetRepositoryDetails;
import es.jcescudero15.mygithubapp.ui.contract.RepositoryDetailsContract;
import es.jcescudero15.mygithubapp.ui.mapper.ViewModelMapper;
import es.jcescudero15.mygithubapp.utils.di.scopes.ActivityScoped;
import io.reactivex.observers.DisposableObserver;

@ActivityScoped
public class RepositoryDetailsPresenter implements RepositoryDetailsContract.Presenter {

    private RepositoryDetailsContract.View mView;
    private GetRepositoryDetails mGetRepositoryDetails;

    @Inject
    RepositoryDetailsPresenter(GetRepositoryDetails getRepositoryDetails) {
        this.mGetRepositoryDetails = getRepositoryDetails;
    }


    @Override
    public void getRepositoryDetails(String repoOwner, String repoName) {
        mView.showProgress();
        mGetRepositoryDetails.execute(new DisposableObserver<Repository>() {
            @Override
            public void onNext(Repository repository) {
                mView.showRepositoryDetails(ViewModelMapper.transform(repository));
            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMessage();
            }

            @Override
            public void onComplete() {
                mView.hideProgress();
            }
        }, new GetRepositoryDetails.Params(repoOwner, repoName));
    }


    @Override
    public void takeView(RepositoryDetailsContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mGetRepositoryDetails.dispose();
        mView = null;
    }

}
