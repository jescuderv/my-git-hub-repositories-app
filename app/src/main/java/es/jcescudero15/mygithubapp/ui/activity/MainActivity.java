package es.jcescudero15.mygithubapp.ui.activity;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import es.jcescudero15.mygithubapp.R;
import es.jcescudero15.mygithubapp.ui.fragment.RepositoriesFragment;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    RepositoriesFragment mRepositoriesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame_layout, mRepositoriesFragment)
                .commit();
    }
}
