package es.jcescudero15.mygithubapp.utils.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import es.jcescudero15.mygithubapp.data.local.LocalDatabase;
import es.jcescudero15.mygithubapp.data.local.RepositoryDAO;
import es.jcescudero15.mygithubapp.data.remote.EndPointInterface;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
abstract class AppModule {

    private final static String BASE_URL = "https://api.github.com/";


    @Binds
    @Singleton
    abstract Context provideContext(Application application);

    @Singleton
    @Provides
    @Named("executor_thread")
    static Scheduler provideExecutorThread() {
        return Schedulers.computation();
    }

    @Singleton
    @Provides
    @Named("main_thread")
    static Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    static EndPointInterface provideApiService() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(EndPointInterface.class);
    }

    @Singleton
    @Provides
    static LocalDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                LocalDatabase.class, "Local.db")
                .build();
    }

    @Singleton
    @Provides
    static RepositoryDAO provideRepositoryDao(LocalDatabase database) {
        return database.repositoryDAO();
    }
}