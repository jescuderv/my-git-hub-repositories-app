package es.jcescudero15.mygithubapp.ui.contract;

import java.util.List;

import es.jcescudero15.mygithubapp.ui.viewmodel.RepositoryViewModel;
import es.jcescudero15.mygithubapp.utils.BasePresenter;

public interface RepositoriesContract {

    interface View {

        void showProgressLoading();

        void hideProgressLoading();

        void showRepositories(List<RepositoryViewModel> repositories);

        void setListLoaded();
    }

    interface Presenter extends BasePresenter<View> {
        void getPublicRepositories(Integer startId);

        void searchPublicRepositories(String query, Integer page);
    }
}
