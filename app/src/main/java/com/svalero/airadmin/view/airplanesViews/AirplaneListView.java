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
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.airadmin.R;
import com.svalero.airadmin.adapter.AirplaneAdapter;
import com.svalero.airadmin.contract.airplanesContracts.AirportListContract;
import com.svalero.airadmin.db.AppDatabase;
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
    private AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_list);

        airplane = new ArrayList<>();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "favoriteAirplaneDao")
                .allowMainThreadQueries()
                .build();

        RecyclerView recyclerView = findViewById(R.id.airplane_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new AirplaneAdapter(airplane,this);
        recyclerView.setAdapter(adapter);
        adapter.setDatabase(appDatabase);

        presenter = new AirplaneListPresenter(this);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this::loadStationsData);



        Intent intent = getIntent();
        if (intent.hasExtra("Snackbar")) {
            String message = intent.getStringExtra("Snackbar");
            View view = findViewById(R.id.coordinatorLayout);
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        }

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
    public void  showMessage(String message) {
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showMessage(int stringId) {
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar.make(view, getResources().getString(stringId), Snackbar.LENGTH_SHORT).show();
    }


}
