package com.example.android.carrent.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.android.carrent.app.interfaces.UserInterface;
import com.example.android.carrent.app.model.Admin;
import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.model.Contract;
import com.example.android.carrent.app.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseUtil {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public ArrayList<Car> getCars() {
        final ArrayList<Car> list = new ArrayList<>();

        final DatabaseReference ref = databaseReference.child("cars");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot carDataSnapshot : dataSnapshot.getChildren()) {
                    Car car = carDataSnapshot.getValue(Car.class);
                    car.setID(carDataSnapshot.getKey());
                    list.add(car);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return list;
    }


    public ArrayList<UserInterface> getUsers() {
        final ArrayList<UserInterface> list = new ArrayList<>();

        final DatabaseReference ref = databaseReference.child("users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    User user = userDataSnapshot.getValue(User.class);
                    user.setID(userDataSnapshot.getKey());
                    list.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final DatabaseReference ref3 = databaseReference.child("admins");

        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adminDataSnapshot : dataSnapshot.getChildren()) {
                    Admin admin = adminDataSnapshot.getValue(Admin.class);
                    list.add(admin);
                    admin.setID(adminDataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return list;
    }

    public void deleteCar(String id) {
        DatabaseReference ref = databaseReference.child("cars").child(id);
        ref.removeValue();
    }

    public void rent(String id, Context context) {
        DatabaseReference ref = databaseReference.child("cars").child(id).child("rented");
        ref.setValue(true);

        DatabaseReference ref2 = databaseReference.child("contracts");

        Contract contract = new Contract(getFromSharedPreferences(context), ref.child("cars").child(id).getKey());
        ref2.push().setValue(contract);
    }

    public void addCar(Car carObj) {
        databaseReference.child("cars").push().setValue(carObj);
    }

    public void addUser(Context context, User user) {
        addtToSharedPreferences(user.getName(), context);
        databaseReference.child("users").push().setValue(user);
    }

    public void addtToSharedPreferences(String name, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.carrent.app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("User", name).apply();
    }

    public String getFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.carrent.app", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("User", "");
        return user;
    }
}
