package com.svalero.airadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.svalero.airadmin.R;
import com.svalero.airadmin.domain.FavoriteAirplane;

import java.util.List;

public class FavoriteAirplaneAdapter extends RecyclerView.Adapter<FavoriteAirplaneAdapter.FavoriteAirplaneHolder> {
    private List<FavoriteAirplane> favoriteAirplane;

    public FavoriteAirplaneAdapter(List<FavoriteAirplane> favoriteAirplane) {
        this.favoriteAirplane = favoriteAirplane;
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

        holder.airplaneId.setText(favoriteAirplane.getId());
        holder.airplaneImageFav.setImageResource(android.R.drawable.star_big_off);
    }

    @Override
    public int getItemCount() {
        return favoriteAirplane.size();
    }

    public static class FavoriteAirplaneHolder extends RecyclerView.ViewHolder {
        public TextView airplaneId;
        public ImageView airplaneImageFav;

        public FavoriteAirplaneHolder(@NonNull View view) {
            super(view);

            airplaneId = view.findViewById(R.id.airplane_item_fav_id);
            airplaneImageFav = view.findViewById(R.id.airplane_item_fav_star);
        }
    }
}
