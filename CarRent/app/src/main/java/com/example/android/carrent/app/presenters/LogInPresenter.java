package com.example.android.carrent.app.presenters;

import android.content.Context;
import android.widget.Toast;

import com.example.android.carrent.app.interfaces.UserInterface;
import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.model.User;
import com.example.android.carrent.app.util.FirebaseUtil;
import com.example.android.carrent.app.view.MainActivity;
import com.example.android.carrent.app.view.fragments.LogInFragment;
import com.example.android.carrent.app.view.fragments.SignInFragment;

import java.util.ArrayList;

public class LogInPresenter {

    private FirebaseUtil firebaseUtil = new FirebaseUtil();

    public void checkUserPassword(String usernameString, String passwordString, Context context, LogInFragment.OnFragmentInteractionListener mListener) {
        for (UserInterface userObj : MainActivity.users) {
            if (usernameString != null && passwordString != null) {
                if (userObj.getName().equals(usernameString) && userObj.getPassword().equals(passwordString)) {
                    firebaseUtil.addtToSharedPreferences(usernameString, context);
                    if (userObj instanceof User) {
                        mListener.onLogIn("user");
                    } else {
                        mListener.onLogIn("admin");
                    }
                } else {
                    Toast.makeText(context, "Inexistent user or incorrect password!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Complete all the fields!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<UserInterface> initUsers() {
        return firebaseUtil.getUsers();
    }

    public ArrayList<Car> initCars() {
        return firebaseUtil.getCars();
    }


    public void addUser(User user, Context context, SignInFragment.OnFragmentInteractionListener mListener) {
        firebaseUtil.addUser(context, user);
        MainActivity.users.add(user);
        mListener.onSignIn("user");
    }
}
