package com.example.android.carrent.app.model;

import java.util.Date;

/**
 * Created by Pocol on 5/19/2018.
 */

public class Contract {

    private String user;
    private String car;

    public Contract(String user, String car) {
        this.user = user;
        this.car = car;
    }

    public Contract() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
