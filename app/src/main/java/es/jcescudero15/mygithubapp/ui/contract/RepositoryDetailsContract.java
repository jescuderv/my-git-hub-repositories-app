package es.jcescudero15.mygithubapp.ui.contract;

import es.jcescudero15.mygithubapp.ui.viewmodel.RepositoryViewModel;
import es.jcescudero15.mygithubapp.utils.BasePresenter;

public interface RepositoryDetailsContract {

    interface View {
        void showRepositoryDetails(RepositoryViewModel repository);

        void showErrorMessage();

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter<View> {
        void getRepositoryDetails(String repoOwner, String repoName);
    }
}
