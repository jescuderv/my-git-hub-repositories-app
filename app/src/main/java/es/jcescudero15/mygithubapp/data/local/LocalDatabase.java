package es.jcescudero15.mygithubapp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import es.jcescudero15.mygithubapp.data.local.entity.RepositoryEntity;

@Database(entities = {RepositoryEntity.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract RepositoryDAO repositoryDAO();
}
