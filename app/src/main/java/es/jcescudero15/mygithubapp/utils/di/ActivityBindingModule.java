package es.jcescudero15.mygithubapp.utils.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import es.jcescudero15.mygithubapp.ui.activity.MainActivity;
import es.jcescudero15.mygithubapp.ui.activity.RepositoryDetailsActivity;
import es.jcescudero15.mygithubapp.utils.di.scopes.ActivityScoped;

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = RepositoryDetailsModule.class)
    abstract RepositoryDetailsActivity repositoryDetailsActivity();
}
