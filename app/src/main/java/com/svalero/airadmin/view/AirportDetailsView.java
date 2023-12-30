package com.svalero.airadmin.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.AirportDeleteContract;
import com.svalero.airadmin.contract.AirportDetailsContract;
import com.svalero.airadmin.contract.AirportEditContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.presenter.AirportDeletePresenter;
import com.svalero.airadmin.presenter.AirportDetailsPresenter;

public class AirportDetailsView extends AppCompatActivity implements AirportDetailsContract.View, AirportDeleteContract.View, AirportEditContract.View {

    private long airportId;
    private String name;
    private String foundationYear;
    private double latitude;
    private double longitude;
    private String city;
    private boolean active;
    AirportDetailsContract.Presenter detailsPresenter = new AirportDetailsPresenter(this);
    AirportDeleteContract.Presenter deletePresenter = new AirportDeletePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_details);

        airportId = getIntent().getLongExtra("airport_item_id", 2);
        detailsPresenter.loadOneAirport(airportId);

    }

    public void listOneAirport(Airport airport) {

        TextView airportIdView = findViewById(R.id.airport_details_id);
        TextView airportName = findViewById(R.id.airport_details_name);
        TextView airportCity = findViewById(R.id.airport_details_city);
        TextView airportFoundationYear = findViewById(R.id.airport_details_foundation_year);
        TextView airportLatitude = findViewById(R.id.airport_details_latitude);
        TextView airportLongitude = findViewById(R.id.airport_details_longitude);

        CheckBox active = findViewById(R.id.edit_active);

        String airportIdText = String.valueOf(airportId);
        airportIdView.setText(airportIdText);
        airportName.setText(airport.getName());
        airportCity.setText(airport.getCity());
        airportLatitude.setText(String.valueOf(airport.getLatitude()));
        airportLongitude.setText(String.valueOf(airport.getLongitude()));
        String foundationYear = airport.getFoundationYear();
        airportFoundationYear.setText(foundationYear);

        active.setChecked(airport.isActive());
    }

    public void deleteOneAirport(View view) {
        deletePresenter.deleteOneAirport(airportId);
        showMessage("Aeropuerto eliminado exitosamente");
        Intent intent = new Intent(AirportDetailsView.this, AirportListView.class);
        startActivity(intent);

    }

    public void goEditOneAirport(View view) {

        String airportId = ((TextView) findViewById(R.id.airport_details_id)).getText().toString();
        String name = ((TextView) findViewById(R.id.airport_details_name)).getText().toString();
        String city = ((TextView) findViewById(R.id.airport_details_city)).getText().toString();
        String foundationYear = ((TextView) findViewById(R.id.airport_details_foundation_year)).getText().toString();
        String latitude = ((TextView) findViewById(R.id.airport_details_latitude)).getText().toString();
        String longitude = ((TextView) findViewById(R.id.airport_details_longitude)).getText().toString();
        CheckBox active = findViewById(R.id.edit_active);
        boolean isActive = active.isChecked();

        Intent intent = new Intent(this, AirportEditView.class);
        Log.d("AirportDetailsView", "ID del aeropuerto: " + airportId);
        intent.putExtra("airport_details_id", airportId);
        intent.putExtra("airport_details_name", name);
        intent.putExtra("airport_details_city", city);
        intent.putExtra("airport_details_foundation_year", foundationYear);
        intent.putExtra("airport_details_latitude", latitude);
        intent.putExtra("airport_details_longitude", longitude);

        intent.putExtra("active", isActive);

        view.getContext().startActivity(intent);

    }


    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showMessage(Airport airport) {

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
