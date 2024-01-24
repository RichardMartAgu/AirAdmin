package com.svalero.airadmin.domain;

public class Airplane {

    private long id;


    private String model;

    private String manufacturingDate;

    private int passengerCapacity;

    private float maxSpeed;

    private boolean active;

    private Airline airline;

    public Airplane(long id, String model, String manufacturingDate, int passengerCapacity, float maxSpeed, boolean active, Airline airline) {
        this.id = id;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.passengerCapacity = passengerCapacity;
        this.maxSpeed = maxSpeed;
        this.active = active;
        this.airline = airline;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
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

    public Airline getAirline() {
        return airline;
    }

    public void getAirline(Airline airline) {
        this.airline = airline;
    }


}