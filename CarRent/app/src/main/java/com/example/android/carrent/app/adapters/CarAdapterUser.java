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

public class CarAdapterUser extends RecyclerView.Adapter<CarAdapterUser.CarViewHolder> {

    private ArrayList<Car> cars;
    private Context context;

    public class CarViewHolder extends RecyclerView.ViewHolder {

        public TextView carName;
        public TextView carPrice;
        public ImageView detailsImage;
        public CarViewHolder(View view) {
            super(view);
            this.carName = view.findViewById(R.id.carNameText);
            this.carPrice = view.findViewById(R.id.priceText);
            this.detailsImage = view.findViewById(R.id.detailsBtn);
        }

    }

    public CarAdapterUser(ArrayList<Car> cars, Context context) {
        this.cars = cars;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_car_user, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder,  int position) {
        final Car car = cars.get(position);
        holder.carName.setText(car.getName());
        holder.carPrice.setText(car.getPrice().toString());
        holder.detailsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof MainActivity){
                    ((MainActivity)context).showDetails(car.getID());
                    notifyDataSetChanged();
                }
            }
        });
    }
}
