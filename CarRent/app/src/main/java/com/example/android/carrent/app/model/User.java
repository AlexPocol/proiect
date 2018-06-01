package com.example.android.carrent.app.model;

import com.example.android.carrent.app.interfaces.UserInterface;

/**
 * Created by Pocol on 5/19/2018.
 */

public class User implements UserInterface {

    private String ID;
    private String name;
    private String email;
    private String password;
    private String address;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public User(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User(String ID, String name, String email, String password, String address) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
