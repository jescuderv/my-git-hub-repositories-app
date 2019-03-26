package es.jcescudero15.mygithubapp.data.remote.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchRepositoriesResultDTO {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<RepositoryDTO> repositoryDTOS;


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<RepositoryDTO> getRepositoryDTOS() {
        return repositoryDTOS;
    }

    public void setRepositoryDTOS(List<RepositoryDTO> repositoryDTOS) {
        this.repositoryDTOS = repositoryDTOS;
    }
}
