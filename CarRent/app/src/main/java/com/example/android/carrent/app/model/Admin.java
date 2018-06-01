package com.example.android.carrent.app.model;

import com.example.android.carrent.app.interfaces.UserInterface;

import java.util.ArrayList;

/**
 * Created by Pocol on 5/19/2018.
 */

public class Admin implements UserInterface {

    private String ID;
    private String name;
    private String email;
    private String password;
    private String address;
    private ArrayList<Car> cars;

    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Admin(String name, String email, String password, String address, ArrayList<Car> cars) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cars = cars;
    }

    public Admin(String ID, String name, String email, String password, String address, ArrayList<Car> cars) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cars = cars;
    }

    @Override
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
