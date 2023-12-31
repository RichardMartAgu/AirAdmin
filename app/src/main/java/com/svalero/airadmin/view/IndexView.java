package com.svalero.airadmin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.svalero.airadmin.R;
import com.svalero.airadmin.view.airplanesViews.AirplaneListView;
import com.svalero.airadmin.view.airportsViews.AirportListView;

public class IndexView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    public void goListAirports(View view){
        Intent intent = new Intent(this, AirportListView.class);
        startActivity(intent);
    }
    public void goListAirplanes(View view){
        Intent intent = new Intent(this, AirplaneListView.class);
        startActivity(intent);
    }
}