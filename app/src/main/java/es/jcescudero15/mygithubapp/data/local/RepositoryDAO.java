package es.jcescudero15.mygithubapp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import es.jcescudero15.mygithubapp.data.local.entity.RepositoryEntity;
import io.reactivex.Maybe;

@Dao
public interface RepositoryDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addRepository(RepositoryEntity repositoryEntity);

    @Query("SELECT * FROM RepositoryEntity WHERE id > :startId LIMIT 100")
    Maybe<List<RepositoryEntity>> getRepositories(Integer startId);
}
