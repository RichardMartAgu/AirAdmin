package com.svalero.airadmin.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.airadmin.R;
import com.svalero.airadmin.db.AppDatabase;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.domain.FavoriteAirplane;
import com.svalero.airadmin.view.airplanesViews.AirplaneDetailsView;

import java.util.List;

public class AirplaneAdapter extends RecyclerView.Adapter<AirplaneAdapter.AirplaneHolder> {

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

        final AppDatabase db = Room.databaseBuilder(holder.parentView.getContext(), AppDatabase.class, "favoriteAirplaneDao")
                .allowMainThreadQueries().build();

        long airplaneId = airplane.getId();

        FavoriteAirplane favoriteAirplane = db.favoriteAirplaneDao().getFavoriteAirplane(String.valueOf(airplaneId));

        long roomId = (favoriteAirplane != null) ? Long.parseLong(favoriteAirplane.getId()) : -1;

        if (airplaneId == roomId) {
            holder.airplaneFavBtn.setImageResource(android.R.drawable.star_big_on);
            holder.airplaneFavBtn.setSelected(true);
        } else {
            holder.airplaneFavBtn.setImageResource(android.R.drawable.star_big_off);
            holder.airplaneFavBtn.setSelected(false);
        }


        holder.airplaneFavBtn.setOnClickListener(v -> {

            FavoriteAirplane updatedFavoriteAirplane = db.favoriteAirplaneDao().getFavoriteAirplane(String.valueOf(airplaneId));


            if (updatedFavoriteAirplane != null) {
                db.favoriteAirplaneDao().delete(updatedFavoriteAirplane);
                holder.airplaneFavBtn.setImageResource(android.R.drawable.star_big_off);
                holder.airplaneFavBtn.setSelected(false);
            } else {
                FavoriteAirplane newFavoriteAirplane = new FavoriteAirplane(String.valueOf(airplaneId), null, null);
                db.favoriteAirplaneDao().insert(newFavoriteAirplane);
                holder.airplaneFavBtn.setImageResource(android.R.drawable.star_big_on);
                holder.airplaneFavBtn.setSelected(true);
            }
        });
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
        public ImageButton airplaneFavBtn;

        public AirplaneHolder(@NonNull View view) {
            super(view);
            parentView = view;

            airplaneId = view.findViewById(R.id.airplane_item_id);
            airplaneModel = view.findViewById(R.id.airplane_item_model);
            detailsButton = view.findViewById(R.id.button_details_airplane);
            airplaneFavBtn = view.findViewById(R.id.airplane_fav_btn);
            detailsButton.setOnClickListener(v -> goDetailsAirplane(view));

        }

    }

    public void goDetailsAirplane(View itemView) {
        long airplaneId = Long.parseLong(((TextView) itemView.findViewById(R.id.airplane_item_id)).getText().toString());
        boolean airplaneFavorite = itemView.findViewById(R.id.airplane_fav_btn).isActivated();
        Intent intent = new Intent(itemView.getContext(), AirplaneDetailsView.class);
        intent.putExtra("airplane_item_id", airplaneId);
        intent.putExtra("airplane_fav_btn", airplaneFavorite);
        itemView.getContext().startActivity(intent);
    }
}

