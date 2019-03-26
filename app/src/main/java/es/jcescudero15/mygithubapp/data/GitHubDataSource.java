package es.jcescudero15.mygithubapp.data;


import java.util.List;

import es.jcescudero15.mygithubapp.domain.model.Repository;
import io.reactivex.Observable;

public interface GitHubDataSource {

    Observable<List<Repository>> getPublicRepositories(Integer startId);

    Observable<List<Repository>> searchPublicRepositories(String query, Integer page);

    Observable<Repository> getRepositoryDetails(String repoOwner, String repoName);

    void addRepositories(List<Repository> repositoryList);
}
