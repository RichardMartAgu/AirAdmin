package com.svalero.airadmin.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.svalero.airadmin.domain.FavoriteAirplane;

import java.util.List;

@Dao
public interface FavoriteAirplaneDao {


    @Query("SELECT * FROM favoriteAirplane")
    List<FavoriteAirplane> getAll();

    @Query("SELECT * FROM favoriteAirplane WHERE id = :id")
    FavoriteAirplane getFavoriteAirplane(String id);

    @Insert
    void insert(FavoriteAirplane favoriteAirplane);

    @Delete
    void delete(FavoriteAirplane favoriteAirplane);

}

