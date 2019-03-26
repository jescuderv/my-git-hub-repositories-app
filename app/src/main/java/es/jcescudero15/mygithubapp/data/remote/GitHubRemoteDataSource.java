package es.jcescudero15.mygithubapp.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.jcescudero15.mygithubapp.data.GitHubDataSource;
import es.jcescudero15.mygithubapp.data.exception.NetworkErrorException;
import es.jcescudero15.mygithubapp.data.mapper.RepositoryMapper;
import es.jcescudero15.mygithubapp.domain.model.Repository;
import io.reactivex.Observable;

@Singleton
public class GitHubRemoteDataSource implements GitHubDataSource {

    private EndPointInterface mEndPointInterface;


    @Inject
    GitHubRemoteDataSource(EndPointInterface endPointInterface) {
        this.mEndPointInterface = endPointInterface;
    }


    @Override
    public Observable<List<Repository>> getPublicRepositories(Integer startId) {
        return mEndPointInterface.getPublicRepositories(startId)
                .onErrorResumeNext(throwable -> {
                    return Observable.error(new NetworkErrorException());
                })
                .map(RepositoryMapper::transformRemote);
    }

    @Override
    public Observable<List<Repository>> searchPublicRepositories(String query, Integer page) {
        return mEndPointInterface.searchPublicRepository(query, page)
                .onErrorResumeNext(throwable -> {
                    return Observable.error(new NetworkErrorException());
                })
                .map(dto -> RepositoryMapper.transformRemote(dto.getRepositoryDTOS()));
    }

    @Override
    public Observable<Repository> getRepositoryDetails(String repoOwner, String repoName) {
        return mEndPointInterface.getRepositoryDetails(repoOwner, repoName)
                .onErrorResumeNext(throwable -> {
                    return Observable.error(new NetworkErrorException());
                })
                .map(RepositoryMapper::transform);
    }

    @Override
    public void addRepositories(List<Repository> repositoryList) {
        // Handled by local data source
    }
}
