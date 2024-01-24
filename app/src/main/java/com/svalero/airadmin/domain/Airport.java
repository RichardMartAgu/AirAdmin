package com.svalero.airadmin.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

public class Airport {

    private Long id;

    private  String name;

    private String city;

    private String foundationYear;

    private double latitude;

    private double longitude;

    private boolean active;

    public Airport(long id, @NonNull String name, @NonNull String city, @NonNull String foundationYear, double latitude, double longitude, boolean active) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.foundationYear = foundationYear;
        this.latitude = latitude;
        this.longitude = longitude;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(@NonNull String foundationYear) {
        this.foundationYear = foundationYear;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
