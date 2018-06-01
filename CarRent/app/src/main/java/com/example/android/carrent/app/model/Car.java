package com.example.android.carrent.app.model;

import java.util.Date;

/**
 * Created by Pocol on 5/19/2018.
 */

public class Car {

    private String ID;
    private String name;
    private String description;
    private Integer price;
    private Date startDate;
    private Date endDate;
    private User renter;
    private boolean rented;
    private String admin;

    public Car() {
    }

    public Car(String ID, String name, String description, Integer price, Date startDate, Date endDate, User renter, boolean rented, String admin) {
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

    public Car(String name, Integer price) {
        this.ID = null;
        this.name = name;
        this.description = null;
        this.price = price;
        this.startDate = null;
        this.endDate = null;
        this.renter = null;
        this.rented = false;
        this.admin = null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

}
