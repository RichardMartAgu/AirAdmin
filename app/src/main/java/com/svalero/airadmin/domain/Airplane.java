package com.svalero.airadmin.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import java.time.LocalDate;

public class Airplane {


    private long id;

    @NonNull
    @ColumnInfo
    private String model;
    @NonNull
    @ColumnInfo
    private String manufacturingDate;
    @ColumnInfo
    private int passengerCapacity;
    @ColumnInfo
    private float maxSpeed;
    @ColumnInfo
    private boolean active;


    public Airplane(long id, @NonNull String model, @NonNull String manufacturingDate, int passengerCapacity, float maxSpeed, boolean active) {
        this.id = id;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.passengerCapacity = passengerCapacity;
        this.maxSpeed = maxSpeed;
        this.active = active;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(@NonNull String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

