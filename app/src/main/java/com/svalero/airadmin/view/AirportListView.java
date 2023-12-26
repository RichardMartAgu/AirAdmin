package com.svalero.airadmin.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.airadmin.R;
import com.svalero.airadmin.adapter.AirportAdapter;
import com.svalero.airadmin.contract.AirportListContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.presenter.AirportListPresenter;

import java.util.ArrayList;
import java.util.List;

public class AirportListView extends AppCompatActivity implements AirportListContract.View {
    private List<Airport> airports;
    private AirportAdapter adapter;
    private AirportListContract.Presenter presenter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_list);

        presenter = new AirportListPresenter(this);

        airports = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.airport_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new AirportAdapter(airports);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllAirports();
    }

    public void goRegisterAirport (View view) {
        Intent intent = new Intent(this, AirportRegisterView.class);
        startActivity(intent);
    }
    @Override
    public void listAirports(List<Airport> airports) {
        this.airports.clear();
        this.airports.addAll(airports);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
