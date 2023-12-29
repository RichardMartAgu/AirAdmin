package com.svalero.airadmin.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.AirportDetailsContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.presenter.AirportDetailsPresenter;

public class AirportDetailsView extends AppCompatActivity implements AirportDetailsContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_details);
        AirportDetailsContract.Presenter presenter = new AirportDetailsPresenter(this);

        long airportId = getIntent().getLongExtra("airport_item_id", 2);
        Log.d("AirportDetailsView", "Received airport_item_id: " + airportId);
        presenter.loadOneAirport(airportId);

    }


    @Override
    public void listOneAirport(Airport airport) {
        TextView airportName = findViewById(R.id.airport_details_name);
        TextView airportCity = findViewById(R.id.airport_details_city);
        TextView airportFoundationYear = findViewById(R.id.airport_details_foundation_year);
        CheckBox active = findViewById(R.id.active);

        airportName.setText(airport.getName());
        airportCity.setText(airport.getCity());
        String foundationYear = airport.getFoundationYear();
        airportFoundationYear.setText(foundationYear);

        if (airport.isActive())
            active.setText(R.string.active);
        else
            active.setText(R.string.inactive);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
