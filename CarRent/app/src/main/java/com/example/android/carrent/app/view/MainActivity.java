package com.example.android.carrent.app.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.android.carrent.R;
import com.example.android.carrent.app.interfaces.UserInterface;
import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.patterns.FragmentFactory;
import com.example.android.carrent.app.presenters.AdminPresenter;
import com.example.android.carrent.app.presenters.LogInPresenter;
import com.example.android.carrent.app.presenters.UserPresenter;
import com.example.android.carrent.app.view.fragments.AddFragment;
import com.example.android.carrent.app.view.fragments.AdminFragment;
import com.example.android.carrent.app.view.fragments.DetailsFragment;
import com.example.android.carrent.app.view.fragments.LogInFragment;
import com.example.android.carrent.app.view.fragments.SignInFragment;
import com.example.android.carrent.app.view.fragments.UserFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LogInFragment.OnFragmentInteractionListener, SignInFragment.OnFragmentInteractionListener, AdminFragment.OnFragmentInteractionListener, UserFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener, AddFragment.OnFragmentInteractionListener {

    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private FloatingActionButton fab;
    private LogInPresenter logInPresenter = new LogInPresenter();
    private AdminPresenter adminPresenter = new AdminPresenter();
    private UserPresenter userPresenter = new UserPresenter();

    public static ArrayList<UserInterface> users = new ArrayList<>();
    public static ArrayList<Car> cars = new ArrayList<>();

    public static final String ADD = "ADD";
    public static final String SIGN = "SIGN";
    public static final String LOG = "LOG";
    public static final String DETAILS = "DETAILS";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        cars = logInPresenter.initCars();
        users = logInPresenter.initUsers();

//        SharedPreferences sharedPreferences = getSharedPreferences("com.example.android.carrent.app", Context.MODE_PRIVATE);
//        String user = sharedPreferences.getString("User", "");
//        if (!user.equals("") && !user.equals("out")) {
//            for (UserInterface userObj : users) {
//                if (userObj.getName().equals(user)) {
//                    if (userObj instanceof User) {
//                        onLogIn("user");
//                    } else {
//                        onLogIn("admin");
//                    }
//                }
//            }
//
//        } else {
        LogInFragment logInFragment = LogInFragment.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main, logInFragment, LOG).commit();
        // }

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFragment addFragment = AddFragment.newInstance();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.main, addFragment, ADD).commit();
            }
        });
        fab.hide();

    }


    public void showFloatingButton() {
        fab.show();
    }

    public void hideFloatingButton() {
        fab.hide();
    }

    public void deleteCar(String id) {
        adminPresenter.deleteCar(id);
        android.support.v4.app.Fragment fragment = FragmentFactory.getFragment("admin");
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.main, fragment, ADMIN).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.log_out) {
            LogInFragment logInFragment = LogInFragment.newInstance();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.main, logInFragment, LOG).commit();
            SharedPreferences sharedPreferences = getSharedPreferences("com.example.android.carrent.app", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("User", "out").apply();
        }
        return true;
    }

    @Override
    public void onNewAccount() {
        SignInFragment signInFragment = SignInFragment.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.main, signInFragment, SIGN).commit();
    }

    @Override
    public void onLogIn(String type) {
        android.support.v4.app.Fragment fragment = FragmentFactory.getFragment(type);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        if (type.equals("user")) {
            fragmentTransaction.replace(R.id.main, fragment, USER).commit();
        } else {
            fragmentTransaction.replace(R.id.main, fragment, ADMIN).commit();
        }
    }

    @Override
    public void onSignIn(String type) {
        android.support.v4.app.Fragment fragment = FragmentFactory.getFragment(type);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.main, fragment, USER).commit();
    }


    @Override
    public void onAdmin() {

    }

    @Override
    public void onUser() {

    }

    @Override
    public void onDetailsInteraction(String id) {
        userPresenter.rent(id, getBaseContext());

        android.support.v4.app.Fragment fragment = FragmentFactory.getFragment("user");
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.main, fragment, USER).commit();
    }

    @Override
    public void onAddFragment() {
        android.support.v4.app.Fragment fragment = FragmentFactory.getFragment("admin");
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.main, fragment, ADMIN).commit();
    }

    public void showDetails(String id) {
        DetailsFragment detailsFragment = DetailsFragment.newInstance();

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        detailsFragment.setArguments(bundle);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.main, detailsFragment, DETAILS).commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.findFragmentByTag(DETAILS) != null) {
            android.support.v4.app.Fragment fragment = FragmentFactory.getFragment("user");
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.main, fragment, USER).commit();
        }

        if (fragmentManager.findFragmentByTag(ADD) != null) {
            android.support.v4.app.Fragment fragment = FragmentFactory.getFragment("admin");
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.main, fragment, ADMIN).commit();
        }

        if (fragmentManager.findFragmentByTag(SIGN) != null) {
            LogInFragment logInFragment = LogInFragment.newInstance();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.main, logInFragment, LOG).commit();
        }

        if (fragmentManager.findFragmentByTag(LOG) != null || fragmentManager.findFragmentByTag(USER) != null || fragmentManager.findFragmentByTag(ADMIN) != null) {
            super.onBackPressed();
        }
    }
}
