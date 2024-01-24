package com.svalero.airadmin.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.airadmin.R;
import com.svalero.airadmin.db.AppDatabase;
import com.svalero.airadmin.domain.FavoriteAirplane;
import com.svalero.airadmin.view.airplanesViews.AirplaneListView;
import com.svalero.airadmin.view.airplanesViews.FavoritesAirplaneListView;
import com.svalero.airadmin.view.airportsViews.AirportDetailsView;
import com.svalero.airadmin.view.airportsViews.AirportListView;

import java.util.List;

public class FavoriteAirplaneAdapter extends RecyclerView.Adapter<FavoriteAirplaneAdapter.FavoriteAirplaneHolder> {
    private List<FavoriteAirplane> favoriteAirplane;
    private AppDatabase database;
    private FavoritesAirplaneListView parentActivity;


    public FavoriteAirplaneAdapter(List<FavoriteAirplane> favoriteAirplane, FavoritesAirplaneListView parentActivity) {
        this.favoriteAirplane = favoriteAirplane;
        this.parentActivity = parentActivity;
    }
    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @NonNull
    @Override
    public FavoriteAirplaneAdapter.FavoriteAirplaneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airplane_list_fav_items, parent, false);
        return new FavoriteAirplaneAdapter.FavoriteAirplaneHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAirplaneAdapter.FavoriteAirplaneHolder holder, int position) {
        FavoriteAirplane favoriteAirplane = this.favoriteAirplane.get(position);

        holder.favAirplaneId.setText(favoriteAirplane.getId());
        holder.favAirplaneImageFav.setImageResource(android.R.drawable.star_big_on);
        holder.favAirplaneModel.setText(favoriteAirplane.getModel());
        holder.favAirplanePassengerCapacity.setText(String.valueOf(favoriteAirplane.getPassengerCapacity()));
        holder.favAirplaneMaxSpeed.setText(String.valueOf(favoriteAirplane.getMaxSpeed()));
        holder.favAirplaneComment.setText(favoriteAirplane.getComment());

        holder.favAirplaneUpdateCommentBtn.setOnClickListener(v -> {

            String airplaneId = favoriteAirplane.getId();
            String newComment = String.valueOf(holder.favAirplaneEditText.getText());
            Log.d("COMMENT", newComment);
            Log.d("FavoriteID null", airplaneId);
            database.favoriteAirplaneDao().updateComment(airplaneId, newComment);
            holder.favAirplaneEditText.setText("");

            parentActivity.showMessage(R.string.add_comment);

        });
    }

    @Override
    public int getItemCount() {
        return favoriteAirplane.size();
    }

    public static class FavoriteAirplaneHolder extends RecyclerView.ViewHolder {
        public TextView favAirplaneId;
        public ImageView favAirplaneImageFav;
        public TextView favAirplaneModel;
        public TextView favAirplanePassengerCapacity;
        public TextView favAirplaneMaxSpeed;
        public TextView favAirplaneComment;
        public EditText favAirplaneEditText;
        public Button favAirplaneUpdateCommentBtn;


        public FavoriteAirplaneHolder(@NonNull View view) {
            super(view);

            favAirplaneId = view.findViewById(R.id.airplane_item_fav_id);
            favAirplaneImageFav = view.findViewById(R.id.airplane_item_fav_star);
            favAirplaneModel = view.findViewById(R.id.airplane_item_fav_model);
            favAirplanePassengerCapacity = view.findViewById(R.id.airplane_item_fav_passenger_capacity);
            favAirplaneMaxSpeed = view.findViewById(R.id.airplane_item_fav_speed);
            favAirplaneComment = view.findViewById((R.id.airplane_item_fav_comment));
            favAirplaneEditText = view.findViewById(R.id.airplane_fav_items_edit_text);
            favAirplaneUpdateCommentBtn = view.findViewById(R.id.airplane_fav_new_coment);

        }
    }

}
