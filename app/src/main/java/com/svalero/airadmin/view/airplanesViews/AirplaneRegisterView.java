package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneRegisterContract;
import com.svalero.airadmin.domain.Airline;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneRegisterPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;
import com.svalero.airadmin.view.airportsViews.AirportListView;

public class AirplaneRegisterView extends AppCompatActivity implements AirplaneRegisterContract.View {

    private AirplaneRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_register);
        presenter = new AirplaneRegisterPresenter(this, this);

    }


    public void createAirplane(View view) {
        EditText addModel = findViewById(R.id.add_airplane_model);
        EditText addManufacturingDate = findViewById(R.id.add_airplane_manufacturing_date);
        EditText addPassengerCapacity = findViewById(R.id.add_airplane_passenger_capacity);
        EditText addMaxSpeed = findViewById(R.id.add_airplane_max_speed);
        EditText addAirlineId = findViewById(R.id.add_airplane_airline_id);
        CheckBox checkActive = findViewById(R.id.add_airplane_active);

        if (ValidatorUtil.areEditTextsValid(addModel, addManufacturingDate, addPassengerCapacity, addMaxSpeed, addAirlineId)) {
            String model = addModel.getText().toString();
            String manufacturingDate = addManufacturingDate.getText().toString();
            int passengerCapacity = Integer.parseInt(addPassengerCapacity.getText().toString());
            float maxSpeed = Float.parseFloat(addMaxSpeed.getText().toString());
            long airlineId = Long.parseLong(addAirlineId.getText().toString());
            Airline airline = new Airline(airlineId);

            boolean active = checkActive.isChecked();

            Airplane airplane = new Airplane(0, model, manufacturingDate, passengerCapacity, maxSpeed, active, airline);
            presenter.registerAirplane(airplane);

        } else {
            showMessage(R.string.field_incomplete);
        }
    }

    @Override
    public void showMessage(String message) {
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showMessage(int stringId) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getResources().getString(stringId), Snackbar.LENGTH_SHORT);
        snackbar.setAction(R.string.go_list, view1 -> {
                    Intent intent = new Intent(this, AirportListView.class);
                    startActivity(intent);
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_blue_light));
        snackbar.show();
    }
}