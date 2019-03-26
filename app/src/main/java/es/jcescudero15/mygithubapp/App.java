package es.jcescudero15.mygithubapp;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import es.jcescudero15.mygithubapp.utils.di.DaggerAppComponent;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
