package com.svalero.airadmin.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.airadmin.R;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.view.AirportDetailsView;

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
                .inflate(R.layout.airport_list_items, parent, false);
        return new AirportHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportAdapter.AirportHolder holder, int position) {
        Airport airport = this.airport.get(position);

        holder.airportId.setText(String.valueOf(airport.getId()));
        holder.airportName.setText(airport.getName());
    }

    @Override
    public int getItemCount() {
        return airport.size();
    }

    public class AirportHolder extends RecyclerView.ViewHolder {

        public TextView airportId;
        public TextView airportName;
        public Button detailsButton;
        public View parentView;

        public AirportHolder(@NonNull View view) {
            super(view);
            parentView = view;

            airportId = view.findViewById(R.id.airport_item_id);
            airportName = view.findViewById(R.id.airport_item_name);
            detailsButton = view.findViewById(R.id.button_details_airport);
            detailsButton.setOnClickListener(v -> goDetailsAirport(view));

        }

    }

    public void goDetailsAirport(View itemView) {
        long airportId = Long.parseLong(((TextView) itemView.findViewById(R.id.airport_item_id)).getText().toString());
        Intent intent = new Intent(itemView.getContext(), AirportDetailsView.class);
        intent.putExtra("airport_item_id", airportId);
        itemView.getContext().startActivity(intent);
    }
}