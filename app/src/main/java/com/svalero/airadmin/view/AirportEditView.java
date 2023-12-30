package com.svalero.airadmin.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.AirportEditContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.presenter.AirportEditPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;

import java.util.Objects;

public class AirportEditView extends AppCompatActivity implements AirportEditContract.View {
    private long airportId;
    private String editName;
    private String editCity;
    private String editFoundationYear;
    double  editLongitude;
    double  editLatitude;
    private AirportEditContract.Presenter editPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_edit);
        editPresenter = new AirportEditPresenter(this, this);
        Intent intent = getIntent();
        Log.d("AirportEditView", "ID del aeropuerto recibido: " + getIntent().getStringExtra("airport_details_id"));
        airportId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("airport_details_id")));
        editName = intent.getStringExtra("airport_details_name");
        editCity = intent.getStringExtra("airport_details_city");
        editFoundationYear = intent.getStringExtra("airport_details_foundation_year");
        double editLongitude = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("airport_details_latitude")));
        double editLatitude = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("airport_details_longitude")));
        boolean isActive = intent.getBooleanExtra("active", false);

        EditText nameView = findViewById(R.id.edit_airport_name);
        EditText cityView = findViewById(R.id.edit_airport_city);
        EditText foundationYearView = findViewById(R.id.edit_airport_foundation_year);
        EditText longitudeView = findViewById(R.id.edit_airport_longitude);
        EditText latitudeView = findViewById(R.id.edit_airport_latitude);
        CheckBox checkActive = findViewById(R.id.edit_active);

        nameView.setText(editName);
        cityView.setText(editCity);
        foundationYearView.setText(editFoundationYear);
        longitudeView.setText(String.valueOf(editLongitude));
        latitudeView.setText(String.valueOf(editLatitude));
        checkActive.setChecked(isActive);

    }

    public void editOneAirport(View view) {

        EditText editName = findViewById(R.id.edit_airport_name);
        EditText editCity = findViewById(R.id.edit_airport_city);
        EditText editFoundationYear = findViewById(R.id.edit_airport_foundation_year);
        EditText editLongitude = findViewById(R.id.edit_airport_longitude);
        EditText editLatitude = findViewById(R.id.edit_airport_latitude);
        CheckBox checkActive = findViewById(R.id.edit_active);

        if (ValidatorUtil.areEditTextsValid(editName, editCity, editFoundationYear, editLongitude, editLatitude)) {
            String name = editName.getText().toString();
            String city = editCity.getText().toString();
            String foundationYear = editFoundationYear.getText().toString();

            double latitude;
            String latitudeText = editLatitude.getText().toString();
            if (!latitudeText.isEmpty()) {
                try {
                    latitude = Double.parseDouble(latitudeText);
                } catch (NumberFormatException e) {
                    showMessage("El campo de latitud debe contener un valor numérico válido");
                    return;
                }
            } else {
                showMessage("Por favor, completa el campo de latitud");
                return;
            }


            double longitude;
            String longitudeText = editLongitude.getText().toString();
            if (!longitudeText.isEmpty()) {
                try {
                    longitude = Double.parseDouble(longitudeText);
                } catch (NumberFormatException e) {
                    showMessage("El campo de longitud debe contener un valor numérico válido");
                    return;
                }
            } else {
                showMessage("Por favor, completa el campo de longitud");
                return;
            }

            boolean active = checkActive.isChecked();

            Airport airport = new Airport(0, name, city, foundationYear, latitude, longitude, active);

            editPresenter.editOneAirport(airportId, airport);

            Intent intent = new Intent(this, AirportListView.class);
            startActivity(intent);


        } else {
            showMessage("Por favor, completa todos los campos");
        }


    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
    }
}
