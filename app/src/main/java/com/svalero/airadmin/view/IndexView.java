package com.svalero.airadmin.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.svalero.airadmin.R;
import com.svalero.airadmin.view.airplanesViews.AirplaneListView;
import com.svalero.airadmin.view.airplanesViews.FavoritesAirplaneListView;
import com.svalero.airadmin.view.airportsViews.AirportListView;

public class IndexView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }


    public void goListAirports(View view) {
        Intent intent = new Intent(this, AirportListView.class);
        startActivity(intent);
    }

    public void goListAirplanes(View view) {
        Intent intent = new Intent(this, AirplaneListView.class);
        startActivity(intent);
    }

    public void goListFavorites(View view) {
        Intent intent = new Intent(this, FavoritesAirplaneListView.class);
        startActivity(intent);
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
}
