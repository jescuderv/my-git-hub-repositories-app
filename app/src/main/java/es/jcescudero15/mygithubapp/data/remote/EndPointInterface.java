package es.jcescudero15.mygithubapp.data.remote;

import java.util.List;

import es.jcescudero15.mygithubapp.data.remote.dto.RepositoryDTO;
import es.jcescudero15.mygithubapp.data.remote.dto.RepositoryDetailsDTO;
import es.jcescudero15.mygithubapp.data.remote.dto.SearchRepositoriesResultDTO;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointInterface {

    @GET("repositories")
    Observable<List<RepositoryDTO>> getPublicRepositories(@Query("since") Integer startId);

    @GET("search/repositories")
    @Headers("Authorization: token 31c1856b5568875b970e83cf503cdf5534991292")
    Observable<SearchRepositoriesResultDTO> searchPublicRepository(@Query("q") String query, @Query("page") Integer page);

    @GET("repos/{owner}/{repo}")
    Observable<RepositoryDetailsDTO> getRepositoryDetails(@Path("owner") String owner, @Path("repo") String repo);
}