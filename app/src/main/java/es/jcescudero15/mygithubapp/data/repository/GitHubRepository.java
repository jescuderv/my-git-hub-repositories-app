package es.jcescudero15.mygithubapp.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.jcescudero15.mygithubapp.data.GitHubDataSource;
import es.jcescudero15.mygithubapp.data.local.GitHubLocalDataSource;
import es.jcescudero15.mygithubapp.data.remote.GitHubRemoteDataSource;
import es.jcescudero15.mygithubapp.domain.model.Repository;
import io.reactivex.Observable;

@Singleton
public class GitHubRepository implements GitHubDataSource {

    private GitHubDataSource mRemoteDataSource;
    private GitHubDataSource mLocalDataSource;


    @Inject
    GitHubRepository(GitHubRemoteDataSource remoteDataSource, GitHubLocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    @Override
    public Observable<List<Repository>> getPublicRepositories(Integer startId) {
        // Get data from local source. If there is not data, get it from remote and save in local.
        return mLocalDataSource.getPublicRepositories(startId)
                .onErrorResumeNext(throwable -> {
                    return mRemoteDataSource.getPublicRepositories(startId)
                            .doOnNext(this::addRepositories);
                });
    }

    @Override
    public Observable<List<Repository>> searchPublicRepositories(String query, Integer page) {
        return mRemoteDataSource.searchPublicRepositories(query, page);
    }

    @Override
    public Observable<Repository> getRepositoryDetails(String repoOwner, String repoName) {
        return mRemoteDataSource.getRepositoryDetails(repoOwner, repoName);
    }

    @Override
    public void addRepositories(List<Repository> repositoryList) {
        mLocalDataSource.addRepositories(repositoryList);
    }
}
