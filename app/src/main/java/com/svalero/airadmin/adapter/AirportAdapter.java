package com.svalero.airadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.airadmin.R;
import com.svalero.airadmin.domain.Airport;

import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.AirportHolder> {

    private List<Airport> airport;

    public AirportAdapter(List<Airport> airport) {
        this.airport = airport;
    }

    @NonNull
    @Override
    public AirportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airport_list_item, parent, false);
        return new AirportHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AirportAdapter.AirportHolder holder, int position) {
        Airport airport = this.airport.get(position);

        holder.airportName.setText(airport.getName());
        holder.airportCity.setText(airport.getCity());
        holder.airportFoundationYear.setText(airport.getFoundationYear());
    }
    @Override
    public int getItemCount() {
        return airport.size();
    }
    public class AirportHolder extends RecyclerView.ViewHolder {

        public TextView airportName;
        public TextView airportCity;
        public TextView airportFoundationYear;
        public View parentView;

        public AirportHolder(@NonNull View view) {
            super(view);
            parentView = view;

            airportName = view.findViewById(R.id.airport_item_name);
            airportCity = view.findViewById(R.id.airport_item_city);

            airportFoundationYear = view.findViewById(R.id.airport_item_FoundationYear);
        }
    }

}
