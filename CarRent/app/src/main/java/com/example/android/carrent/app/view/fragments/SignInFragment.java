package com.example.android.carrent.app.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.carrent.R;
import com.example.android.carrent.app.model.User;
import com.example.android.carrent.app.presenters.LogInPresenter;

public class SignInFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private LogInPresenter logInPresenter = new LogInPresenter();

    public SignInFragment() {
        // Required empty public constructor
    }


    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);

        Button button = v.findViewById(R.id.create_account_btn);
        final EditText username = v.findViewById(R.id.username_edt_signIn);
        final EditText password = v.findViewById(R.id.password_edt_signIn);
        final EditText email = v.findViewById(R.id.email_edt_signIn);
        final EditText address = v.findViewById(R.id.address_edt_signIn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameString;
                String passwordString;
                String emailString;
                String addressString;

                if (email != null && !email.getText().toString().equals("")) {
                    emailString = email.getText().toString();
                } else {
                    emailString = null;
                }

                if (username != null && !username.getText().toString().equals("")) {
                    usernameString = username.getText().toString();
                } else {
                    usernameString = null;
                }

                if (password != null && !password.getText().toString().equals("")) {
                    passwordString = password.getText().toString();
                } else {
                    passwordString = null;
                }

                if (address != null && !address.getText().toString().equals("")) {
                    addressString = address.getText().toString();
                } else {
                    addressString = null;
                }

                if (mListener != null) {
                    if (usernameString != null & emailString != null && passwordString != null && addressString != null) {
                        if (!username.getText().toString().equals("") && !password.getText().toString().equals("") && !email.getText().toString().equals("") && !address.getText().toString().equals("")) {

                            User userObj = new User(usernameString, emailString, passwordString, addressString);

                            logInPresenter.addUser(userObj, getContext(), mListener);
                        }
                    } else {
                        Toast.makeText(getContext(), "Complete all the fields!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onSignIn(String type);
    }
}
