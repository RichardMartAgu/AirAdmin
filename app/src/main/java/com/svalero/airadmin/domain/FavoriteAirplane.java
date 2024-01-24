package com.svalero.airadmin.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteAirplane {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo
    private String model;
    @ColumnInfo
    private int passengerCapacity;
    @ColumnInfo
    private float maxSpeed;
    @ColumnInfo
    private String comment;
    @ColumnInfo
    private Boolean favorite;


    public FavoriteAirplane(@NonNull String id, String model, int passengerCapacity, float maxSpeed,String comment, Boolean favorite) {
        this.id = id;
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.maxSpeed = maxSpeed;
        this.comment = comment;
        this.favorite = favorite;
    }



    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String name) {
        this.model = name;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
