package com.svalero.airadmin.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.airadmin.domain.FavoriteAirplane;

@Database(entities = {FavoriteAirplane.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteAirplaneDao favoriteAirplaneDao();
}
