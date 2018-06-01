package com.example.android.carrent.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.carrent.R;
import com.example.android.carrent.app.model.Car;
import com.example.android.carrent.app.view.MainActivity;

import java.util.ArrayList;

public class CarAdapterAdmin extends RecyclerView.Adapter<CarAdapterAdmin.CarViewHolder> {

    private ArrayList<Car> cars;
    private Context context;

    public class CarViewHolder extends RecyclerView.ViewHolder {

        public TextView carName;
        public TextView carPrice;
        public ImageView deleteImage;
        public ImageView rentedImage;

        public CarViewHolder(View view) {
            super(view);
            this.carName = view.findViewById(R.id.carNameText);
            this.carPrice = view.findViewById(R.id.priceText);
            this.deleteImage = view.findViewById(R.id.deleteBtn);
            this.rentedImage = view.findViewById(R.id.rentedBtn);
        }

    }

    public CarAdapterAdmin(ArrayList<Car> cars, Context context) {
        this.context = context;
        this.cars = cars;
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_car_admin, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder,  int position) {
        Car car = cars.get(position);
        holder.carName.setText(car.getName());
        holder.carPrice.setText(car.getPrice().toString());
        final String ID = car.getID();

        if(car.isRented()) {
            holder.rentedImage.setVisibility(View.VISIBLE);
        } else {
            holder.rentedImage.setVisibility(View.GONE);
        }

        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ((MainActivity)context).deleteCar(ID);
                notifyDataSetChanged();
            }
        });

    }
}
