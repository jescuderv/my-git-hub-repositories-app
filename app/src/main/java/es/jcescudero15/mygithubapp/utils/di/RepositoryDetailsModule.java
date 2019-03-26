package es.jcescudero15.mygithubapp.utils.di;

import dagger.Binds;
import dagger.Module;
import es.jcescudero15.mygithubapp.ui.contract.RepositoryDetailsContract;
import es.jcescudero15.mygithubapp.ui.presenter.RepositoryDetailsPresenter;
import es.jcescudero15.mygithubapp.utils.di.scopes.ActivityScoped;

@Module
abstract class RepositoryDetailsModule {

    @ActivityScoped
    @Binds
    abstract RepositoryDetailsContract.Presenter provideRepositoryDetailsPresenter(RepositoryDetailsPresenter presenter);
}
