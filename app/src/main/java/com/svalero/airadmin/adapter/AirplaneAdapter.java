package com.svalero.airadmin.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.airadmin.R;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.view.airplanesViews.AirplaneDetailsView;

import java.util.List;

public class AirplaneAdapter extends RecyclerView.Adapter<AirplaneAdapter.AirplaneHolder>  {

    private List<Airplane> airplane;


    public AirplaneAdapter(List<Airplane> airplane) {
        this.airplane = airplane;

    }

    @NonNull
    @Override
    public AirplaneAdapter.AirplaneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airplane_list_items, parent, false);
        return new AirplaneAdapter.AirplaneHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirplaneAdapter.AirplaneHolder holder, int position) {
        Airplane airplane = this.airplane.get(position);

        holder.airplaneId.setText(String.valueOf(airplane.getId()));
        holder.airplaneModel.setText(airplane.getModel());
    }

    @Override
    public int getItemCount() {
        return airplane.size();
    }

    public class AirplaneHolder extends RecyclerView.ViewHolder {

        public TextView airplaneId;
        public TextView airplaneModel;
        public Button detailsButton;
        public View parentView;

        public AirplaneHolder(@NonNull View view) {
            super(view);
            parentView = view;

            airplaneId = view.findViewById(R.id.airplane_item_id);
            airplaneModel = view.findViewById(R.id.airplane_item_model);
            detailsButton = view.findViewById(R.id.button_details_airplane);
            detailsButton.setOnClickListener(v -> goDetailsAirplane(view));

        }

    }

    public void goDetailsAirplane(View itemView) {
        long airplaneId = Long.parseLong(((TextView) itemView.findViewById(R.id.airplane_item_id)).getText().toString());
        Intent intent = new Intent(itemView.getContext(), AirplaneDetailsView.class);
        intent.putExtra("airplane_item_id", airplaneId);
        itemView.getContext().startActivity(intent);
    }
}

