package com.example.android.carrent.app.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.carrent.R;
import com.example.android.carrent.app.adapters.CarAdapterAdmin;
import com.example.android.carrent.app.interfaces.UserInterface;
import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.view.MainActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdminFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public AdminFragment() {
        // Required empty public constructor
    }

    public static AdminFragment newInstance() {
        AdminFragment fragment = new AdminFragment();
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
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).hideFloatingButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin, container, false);

        ((MainActivity) getActivity()).showFloatingButton();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.android.carrent.app", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("User", "");

        String userId = "";

        for (UserInterface userObj : MainActivity.users) {
            if (userObj.getName().equals(user)) {
                userId = userObj.getID();
            }
        }

        ArrayList<Car> userCars = new ArrayList<>();

        for (Car car : MainActivity.cars) {
            if (car.getAdmin().equals(userId)) {
                userCars.add(car);
            }
        }

        RecyclerView recyclerView = v.findViewById(R.id.admin_recycler);
        CarAdapterAdmin carAdapterUser = new CarAdapterAdmin(userCars, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(carAdapterUser);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).showFloatingButton();
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

        void onAdmin();
    }
}
