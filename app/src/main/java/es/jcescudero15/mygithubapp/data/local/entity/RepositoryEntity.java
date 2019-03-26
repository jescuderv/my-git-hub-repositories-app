package es.jcescudero15.mygithubapp.data.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RepositoryEntity {

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "owner_name")
    private String ownerName;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "creation_date")
    private String creationDate;

    @ColumnInfo(name = "last_updated_date")
    private String lastUpdatedDate;

    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "forks_count")
    private Integer forksCount;

    @ColumnInfo(name = "stars_count")
    private Integer starsCount;

    @ColumnInfo(name = "observers_count")
    private Integer observersCount;

    @ColumnInfo(name = "git_hub_url")
    private String gitHubUrl;


    public RepositoryEntity(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    public Integer getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Integer starsCount) {
        this.starsCount = starsCount;
    }

    public Integer getObserversCount() {
        return observersCount;
    }

    public void setObserversCount(Integer observersCount) {
        this.observersCount = observersCount;
    }

    public String getGitHubUrl() {
        return gitHubUrl;
    }

    public void setGitHubUrl(String gitHubUrl) {
        this.gitHubUrl = gitHubUrl;
    }
}
