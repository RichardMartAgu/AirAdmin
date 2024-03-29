package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.airadmin.R;
import com.svalero.airadmin.adapter.FavoriteAirplaneAdapter;
import com.svalero.airadmin.db.AppDatabase;
import com.svalero.airadmin.domain.FavoriteAirplane;
import com.svalero.airadmin.view.IndexView;
import com.svalero.airadmin.view.airportsViews.AirportListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FavoritesAirplaneListView extends AppCompatActivity {

    private List<FavoriteAirplane> favoriteAirplane;
    private FavoriteAirplaneAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_airplane_list_favorites);

        favoriteAirplane = new ArrayList<>();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "favoriteAirplaneDao")
                .allowMainThreadQueries()
                .build();

        RecyclerView recyclerView = findViewById(R.id.airplane_list_favorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new FavoriteAirplaneAdapter(favoriteAirplane,this);
        recyclerView.setAdapter(adapter);
        adapter.setDatabase(appDatabase);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this::loadStationsData);

        loadStationsData();
    }

    private void loadStationsData() {
        new Handler().postDelayed(() -> {
            favoriteAirplane.clear();
            favoriteAirplane.addAll(appDatabase.favoriteAirplaneDao().getAll());

            Collections.sort(favoriteAirplane, Comparator.comparingInt(airplane -> Integer.parseInt(airplane.getId())));

            adapter.notifyDataSetChanged();

            swipeRefreshLayout.setRefreshing(false);
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.volver) {
            Intent intent = new Intent(this, IndexView.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.airports) {
            Intent intent = new Intent(this, AirportListView.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.airplanes) {
            Intent intent = new Intent(this, AirplaneListView.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.fav_airplanes) {
            Intent intent = new Intent(this, FavoritesAirplaneListView.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String message) {
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
    public void showMessage(int stringId) {
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar.make(view, stringId, Snackbar.LENGTH_SHORT).show();
    }
}
