package es.jcescudero15.mygithubapp.domain.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import es.jcescudero15.mygithubapp.data.GitHubDataSource;
import es.jcescudero15.mygithubapp.data.repository.GitHubRepository;
import es.jcescudero15.mygithubapp.domain.model.Repository;
import es.jcescudero15.mygithubapp.utils.UseCase;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

@Singleton
public class GetPublicRepositories extends UseCase<List<Repository>, GetPublicRepositories.Params> {

    private GitHubDataSource mRepository;


    @Inject
    GetPublicRepositories(@Named("executor_thread") Scheduler executorThread, @Named("main_thread")
            Scheduler uiThread, GitHubRepository repository) {
        super(executorThread, uiThread);
        mRepository = repository;
    }

    @Override
    protected Observable<List<Repository>> createObservableUseCase(Params params) {
        return mRepository.getPublicRepositories(params.getStartId());
    }


    public final static class Params {
        private Integer startId;

        public Params(Integer startId) {
            this.startId = startId;
        }

        Integer getStartId() {
            return startId;
        }
    }
}
