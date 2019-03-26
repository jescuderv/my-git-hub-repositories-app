package es.jcescudero15.mygithubapp.data.mapper;

import java.util.ArrayList;
import java.util.List;

import es.jcescudero15.mygithubapp.data.local.entity.RepositoryEntity;
import es.jcescudero15.mygithubapp.data.remote.dto.RepositoryDTO;
import es.jcescudero15.mygithubapp.data.remote.dto.RepositoryDetailsDTO;
import es.jcescudero15.mygithubapp.domain.model.Repository;

public class RepositoryMapper {

    public static Repository transformRemote(RepositoryDTO dto) {
        Repository repository = new Repository(dto.getId());

        repository.setName(dto.getName());
        repository.setOwnerName(dto.getOwner().getLogin());
        repository.setDescription(dto.getDescription());
        repository.setImageUrl(dto.getOwner().getAvatarUrl());

        return repository;
    }

    public static List<Repository> transformRemote(List<RepositoryDTO> dtoList) {
        List<Repository> repositoryList = new ArrayList<>();

        for (RepositoryDTO dto : dtoList) {
            repositoryList.add(transformRemote(dto));
        }

        return repositoryList;
    }


    public static Repository transformLocal(RepositoryEntity entity) {
        Repository repository = new Repository(entity.getId());

        repository.setName(entity.getName());
        repository.setOwnerName(entity.getOwnerName());
        repository.setDescription(entity.getDescription());
        repository.setImageUrl(entity.getImageUrl());

        return repository;
    }

    public static List<Repository> transformLocal(List<RepositoryEntity> entityList) {
        List<Repository> repositoryList = new ArrayList<>();

        for (RepositoryEntity entity : entityList) {
            repositoryList.add(transformLocal(entity));
        }

        return repositoryList;
    }

    public static RepositoryEntity transformLocal(Repository repository) {
        RepositoryEntity entity = new RepositoryEntity(repository.getId());

        entity.setName(repository.getName());
        entity.setOwnerName(repository.getOwnerName());
        entity.setDescription(repository.getDescription());
        entity.setImageUrl(repository.getImageUrl());

        return entity;
    }

    public static Repository transform(RepositoryDetailsDTO dto) {
        Repository repository = new Repository(dto.getId());

        repository.setName(dto.getName());
        repository.setOwnerName(dto.getOwner().getLogin());
        repository.setDescription(dto.getDescription());
        repository.setImageUrl(dto.getOwner().getAvatarUrl());
        repository.setCreationDate(dto.getCreatedAt());
        repository.setLastUpdatedDate(dto.getUpdatedAt());
        repository.setLanguage(dto.getLanguage());
        repository.setForksCount(dto.getForksCount());
        repository.setStarsCount(dto.getStargazersCount());
        repository.setObserversCount(dto.getWatchersCount());
        repository.setGitHubUrl(dto.getHtmlUrl());

        return repository;
    }

}
