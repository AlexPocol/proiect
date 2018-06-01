package com.example.android.carrent.app.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.carrent.R;
import com.example.android.carrent.app.presenters.LogInPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogInFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public LogInFragment() {
        // Required empty public constructor
    }


    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
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
        View v = inflater.inflate(R.layout.fragment_log_in, container, false);

        TextView newAccount = v.findViewById(R.id.creare_cont_txt);

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onNewAccount();
                }
            }
        });

        Button button = v.findViewById(R.id.login_btn);
        final EditText username = v.findViewById(R.id.username_edt);
        final EditText password = v.findViewById(R.id.password_edt);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameString;
                String passwordString;
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

                LogInPresenter presenter = new LogInPresenter();

                if (mListener != null) {
                    presenter.checkUserPassword(usernameString, passwordString, getContext(), mListener);
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
        void onNewAccount();

        void onLogIn(String type);
    }
}
