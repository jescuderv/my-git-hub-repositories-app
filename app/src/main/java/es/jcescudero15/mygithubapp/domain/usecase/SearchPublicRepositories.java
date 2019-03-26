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
public class SearchPublicRepositories extends UseCase<List<Repository>, SearchPublicRepositories.Params> {

    private GitHubDataSource mRepository;


    @Inject
    SearchPublicRepositories(@Named("executor_thread") Scheduler executorThread, @Named("main_thread")
            Scheduler uiThread, GitHubRepository repository) {
        super(executorThread, uiThread);
        mRepository = repository;
    }

    @Override
    protected Observable<List<Repository>> createObservableUseCase(SearchPublicRepositories.Params params) {
        return mRepository.searchPublicRepositories(params.getQuery(), params.getPage());
    }


    public final static class Params {
        private String query;
        private Integer page;

        public Params(String query, Integer page) {
            this.query = query;
            this.page = page;
        }

        public String getQuery() {
            return query;
        }

        public Integer getPage() {
            return page;
        }
    }
}
