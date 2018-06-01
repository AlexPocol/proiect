package com.example.android.carrent.app.patterns;

import android.support.v4.app.Fragment;

import com.example.android.carrent.app.view.fragments.AdminFragment;
import com.example.android.carrent.app.view.fragments.UserFragment;

public class FragmentFactory {

    public static Fragment getFragment(String type) {
        if(type.equals("admin")) {
            return AdminFragment.newInstance();
        }
        if(type.equals("user")) {
            return UserFragment.newInstance();
        }

        return null;
    }
}
