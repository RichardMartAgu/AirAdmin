package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.svalero.airadmin.R;
import com.svalero.airadmin.adapter.AirplaneAdapter;
import com.svalero.airadmin.contract.airplanesContracts.AirportListContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneListPresenter;
import com.svalero.airadmin.view.IndexView;
import com.svalero.airadmin.view.airportsViews.AirportListView;

import java.util.ArrayList;
import java.util.List;

public class AirplaneListView extends AppCompatActivity implements AirportListContract.View {
    private List<Airplane> airplane;
    private AirplaneAdapter adapter;
    private AirportListContract.Presenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_list);

        airplane = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.airplane_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new AirplaneAdapter(airplane);
        recyclerView.setAdapter(adapter);

        presenter = new AirplaneListPresenter(this);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this::loadStationsData);

    }

    private void loadStationsData() {
        new Handler().postDelayed(() -> {
            presenter.loadAllAirplanes();
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllAirplanes();
    }

    public void goRegisterAirplane(View view) {
        Intent intent = new Intent(this, AirplaneRegisterView.class);
        startActivity(intent);
    }

    @Override
    public void listAirplane(List<Airplane> airplanes) {
        this.airplane.clear();
        this.airplane.addAll(airplanes);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
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


    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



}
