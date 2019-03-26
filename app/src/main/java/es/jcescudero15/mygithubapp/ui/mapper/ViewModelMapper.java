package es.jcescudero15.mygithubapp.ui.mapper;

import java.util.ArrayList;
import java.util.List;

import es.jcescudero15.mygithubapp.domain.model.Repository;
import es.jcescudero15.mygithubapp.ui.viewmodel.RepositoryViewModel;

public class ViewModelMapper {

    public static RepositoryViewModel transform(Repository repository) {
        RepositoryViewModel repositoryViewModel = new RepositoryViewModel();

        repositoryViewModel.setId(repository.getId());
        repositoryViewModel.setName(repository.getName());
        repositoryViewModel.setOwnerName(repository.getOwnerName());
        repositoryViewModel.setDescription(repository.getDescription());
        repositoryViewModel.setImageUrl(repository.getImageUrl());
        repositoryViewModel.setCreationDate(repository.getCreationDate());
        repositoryViewModel.setLastUpdatedDate(repository.getLastUpdatedDate());
        repositoryViewModel.setLanguage(repository.getLanguage());
        repositoryViewModel.setForksCount(repository.getForksCount());
        repositoryViewModel.setStarsCount(repository.getStarsCount());
        repositoryViewModel.setObserversCount(repository.getObserversCount());
        repositoryViewModel.setGitHubUrl(repository.getGitHubUrl());

        return repositoryViewModel;
    }

    public static List<RepositoryViewModel> transform(List<Repository> repositoryList) {
        List<RepositoryViewModel> viewModelList = new ArrayList<>();

        for (Repository repository : repositoryList) {
            viewModelList.add(transform(repository));
        }

        return viewModelList;
    }

}
