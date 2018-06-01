package com.example.android.carrent.app.patterns;

import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.model.User;

import java.util.Date;

public class CarBuilder {

    private String ID;
    private String name;
    private String description;
    private Integer price;
    private Date startDate;
    private Date endDate;
    private User renter;
    private boolean rented;
    private String admin;

    public CarBuilder(String ID, String name, String description, Integer price, Date startDate, Date endDate, User renter, boolean rented, String admin) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.renter = renter;
        this.rented = rented;
        this.admin = admin;
    }

    public CarBuilder() {

    }

    public CarBuilder setID(String ID) {
        this.ID = ID;
        return this;
    }

    public CarBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CarBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CarBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public CarBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public CarBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public CarBuilder setRenter(User renter) {
        this.renter = renter;
        return this;
    }

    public CarBuilder setRented(boolean rented) {
        this.rented = rented;
        return this;
    }

    public CarBuilder setAdmin(String admin) {
        this.admin = admin;
        return this;
    }

    public Car build() {
        return new Car(ID, name, description, price, startDate, endDate, renter, rented, admin);
    }
}
