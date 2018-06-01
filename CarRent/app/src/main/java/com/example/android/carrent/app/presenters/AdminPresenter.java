package com.example.android.carrent.app.presenters;

import android.content.Context;

import com.example.android.carrent.app.interfaces.UserInterface;
import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.util.FirebaseUtil;
import com.example.android.carrent.app.view.MainActivity;
import com.example.android.carrent.app.view.fragments.AddFragment;

public class AdminPresenter {

    private FirebaseUtil firebaseUtil = new FirebaseUtil();

    public void deleteCar(String id) {
        for (Car car : MainActivity.cars) {
            if (car.getID().equals(id)) {
                MainActivity.cars.remove(car);
            }
        }
        firebaseUtil.deleteCar(id);
    }

    public void addCar(Car carObj, Context context, AddFragment.OnFragmentInteractionListener mListener) {
       String user  = firebaseUtil.getFromSharedPreferences(context);

        for (UserInterface userObj : MainActivity.users) {
            if (userObj.getName().equals(user)) {
                carObj.setAdmin(userObj.getID());
            }
        }

        MainActivity.cars.add(carObj);
        firebaseUtil.addCar(carObj);
        mListener.onAddFragment();
    }
}
