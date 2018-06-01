package com.example.android.carrent.app.presenters;

import android.content.Context;

import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.model.Contract;
import com.example.android.carrent.app.util.FirebaseUtil;
import com.example.android.carrent.app.view.MainActivity;

public class UserPresenter {

    private FirebaseUtil firebaseUtil = new FirebaseUtil();

    public void rent(String id, Context context) {
        for(Car car :  MainActivity.cars) {
            if(car.getID().equals(id)) {
                car.setRented(true);
            }
        }
        firebaseUtil.rent(id, context);
    }
}
