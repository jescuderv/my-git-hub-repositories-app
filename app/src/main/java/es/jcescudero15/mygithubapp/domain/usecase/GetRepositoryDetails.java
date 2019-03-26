package es.jcescudero15.mygithubapp.domain.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import es.jcescudero15.mygithubapp.data.GitHubDataSource;
import es.jcescudero15.mygithubapp.data.repository.GitHubRepository;
import es.jcescudero15.mygithubapp.domain.model.Repository;
import es.jcescudero15.mygithubapp.utils.UseCase;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetRepositoryDetails extends UseCase<Repository, GetRepositoryDetails.Params> {


    private GitHubDataSource mRepository;


    @Inject
    GetRepositoryDetails(@Named("executor_thread") Scheduler executorThread, @Named("main_thread")
            Scheduler uiThread, GitHubRepository repository) {
        super(executorThread, uiThread);
        mRepository = repository;
    }

    @Override
    protected Observable<Repository> createObservableUseCase(GetRepositoryDetails.Params params) {
        return mRepository.getRepositoryDetails(params.getRepositoryOwner(), params.getRepositoryName());
    }


    public final static class Params {
        private String repositoryOwner;
        private String repositoryName;

        public Params(String repositoryOwner, String repositoryName) {
            this.repositoryOwner = repositoryOwner;
            this.repositoryName = repositoryName;
        }

        public String getRepositoryOwner() {
            return repositoryOwner;
        }

        public String getRepositoryName() {
            return repositoryName;
        }
    }
}
