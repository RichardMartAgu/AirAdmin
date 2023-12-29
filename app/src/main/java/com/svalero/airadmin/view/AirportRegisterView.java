package com.svalero.airadmin.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.AirportRegisterContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.presenter.AirportRegisterPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;


public class AirportRegisterView extends AppCompatActivity implements AirportRegisterContract.View {

    private AirportRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_register);
        presenter = new AirportRegisterPresenter(this,this);

    }


    public void createAirport(View view) {
        EditText addName = findViewById(R.id.add_airport_name);
        EditText addCity = findViewById(R.id.add_airport_city);
        EditText addFoundationYear = findViewById(R.id.add_airport_foundation_year);
        EditText addLongitude = findViewById(R.id.add_airport_longitude);
        EditText addLatitude = findViewById(R.id.add_airport_latitude);
        CheckBox checkActive = findViewById(R.id.active);

        if (ValidatorUtil.areEditTextsValid(addName, addCity, addFoundationYear, addLongitude, addLatitude)) {
            String name = addName.getText().toString();
            String city = addCity.getText().toString();
            String foundationYear = addFoundationYear.getText().toString();

            double latitude;
            String latitudeText = addLatitude.getText().toString();
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
            String longitudeText = addLongitude.getText().toString();
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
            presenter.registerAirport(airport);

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
