package com.example.android.carrent.app.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.carrent.R;
import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.patterns.CarBuilder;
import com.example.android.carrent.app.presenters.AdminPresenter;

public class AddFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private AdminPresenter adminPresenter = new AdminPresenter();

    public AddFragment() {
        // Required empty public constructor
    }


    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
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
        View v =  inflater.inflate(R.layout.fragment_add, container, false);


        final EditText carName = v.findViewById(R.id.carNameAdd);
        final EditText carPrice = v.findViewById(R.id.carPriceAdd);
        final EditText carDescription = v.findViewById(R.id.carDescriptionAdd);

        Button add = v.findViewById(R.id.addBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carNameString;
                Integer carPriceString;
                String carDescriptionString;

                if (carName != null && !carName.getText().toString().equals("")) {
                    carNameString = carName.getText().toString();
                } else {
                    carNameString = null;
                }

                if (carPrice != null && !carPrice.getText().toString().equals("")) {
                    carPriceString = Integer.parseInt(carPrice.getText().toString());
                } else {
                    carPriceString = null;
                }

                if (carDescription != null && !carDescription.getText().toString().equals("")) {
                    carDescriptionString = carDescription.getText().toString();
                } else {
                    carDescriptionString = null;
                }


                if (mListener != null) {
                    if (carNameString != null & carPriceString != null && carDescriptionString != null ) {
                        if (!carName.getText().toString().equals("") && !carPrice.getText().toString().equals("") && !carDescription.getText().toString().equals("")) {

                            Car carObj = new CarBuilder().setName(carNameString).setPrice(carPriceString).setDescription(carDescriptionString).build();
                            adminPresenter.addCar(carObj, getContext(), mListener);
                        }
                    } else {
                        Toast.makeText(getContext(), "Complete all the fields!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAddFragment();
        }
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
        // TODO: Update argument type and name
        void onAddFragment();
    }
}
