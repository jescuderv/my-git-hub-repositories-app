package es.jcescudero15.mygithubapp.utils.di;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import es.jcescudero15.mygithubapp.ui.contract.RepositoriesContract;
import es.jcescudero15.mygithubapp.ui.fragment.RepositoriesFragment;
import es.jcescudero15.mygithubapp.ui.presenter.RepositoriesPresenter;
import es.jcescudero15.mygithubapp.utils.di.scopes.ActivityScoped;
import es.jcescudero15.mygithubapp.utils.di.scopes.FragmentScoped;

@Module
public abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract RepositoriesFragment repositoriesFragment();

    @ActivityScoped
    @Binds
    abstract RepositoriesContract.Presenter provideRepositoriesPresenter(RepositoriesPresenter presenter);
}
