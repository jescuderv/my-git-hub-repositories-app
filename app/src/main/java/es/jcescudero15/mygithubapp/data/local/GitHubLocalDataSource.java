package es.jcescudero15.mygithubapp.data.local;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.jcescudero15.mygithubapp.data.GitHubDataSource;
import es.jcescudero15.mygithubapp.data.exception.DatabaseErrorException;
import es.jcescudero15.mygithubapp.data.mapper.RepositoryMapper;
import es.jcescudero15.mygithubapp.domain.model.Repository;
import io.reactivex.Observable;

@Singleton
public class GitHubLocalDataSource implements GitHubDataSource {

    private RepositoryDAO mRepositoryDAO;


    @Inject
    GitHubLocalDataSource(RepositoryDAO repositoryDAO) {
        this.mRepositoryDAO = repositoryDAO;
    }


    @Override
    public Observable<List<Repository>> getPublicRepositories(Integer startId) {
        return mRepositoryDAO.getRepositories(startId)
                .toObservable()
                .doOnError(throwable -> {
                    throw new DatabaseErrorException();
                })
                .map(RepositoryMapper::transformLocal)
                .doOnNext(repositoryList -> {
                    if (repositoryList.size() == 0) throw new DatabaseErrorException();
                });
    }

    @Override
    public Observable<List<Repository>> searchPublicRepositories(String query, Integer page) {
        // Handled by remote data source
        return Observable.empty();
    }

    @Override
    public Observable<Repository> getRepositoryDetails(String repoOwner, String repoName) {
        // Handled by remote data source
        return Observable.empty();
    }

    @Override
    public void addRepositories(List<Repository> repositoryList) {
        for (Repository repository : repositoryList) {
            mRepositoryDAO.addRepository(RepositoryMapper.transformLocal(repository));
        }
    }
}
