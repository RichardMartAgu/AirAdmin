package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneEditContract;
import com.svalero.airadmin.domain.Airline;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneEditPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;
import com.svalero.airadmin.view.airportsViews.AirportListView;

import java.util.Objects;

public class AirplaneEditView extends AppCompatActivity implements AirplaneEditContract.View {


    private long airplaneId;
    private String editModel;
    private String editManufacturingDate;
    private int editPassengerCapacity;
    private float editMaxSpeed;
    private long editAirlineId;

    private AirplaneEditContract.Presenter editPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_edit);
        editPresenter = new AirplaneEditPresenter(this, this);
        Intent intent = getIntent();
        airplaneId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("airplane_details_id")));
        editModel = intent.getStringExtra("airplane_details_model");
        editManufacturingDate = intent.getStringExtra("airplane_details_manufacturing_date");
        editPassengerCapacity = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("airplane_details_passenger_capacity")));
        editMaxSpeed = Float.parseFloat(Objects.requireNonNull(intent.getStringExtra("airplane_details_max_speed")));
        editAirlineId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("airplane_details_airline_id")));

        boolean isActive = intent.getBooleanExtra("airplane_details_active", false);

        EditText modelView = findViewById(R.id.edit_airplane_model);
        EditText manufacturingDateView = findViewById(R.id.edit_airplane_manufacturing_date);
        EditText passengerCapacityView = findViewById(R.id.edit_airplane_passenger_capacity);
        EditText maxSpeedView = findViewById(R.id.edit_airplane_max_speed);
        EditText airlineIdView = findViewById(R.id.edit_airplane_airline_id);

        CheckBox checkActive = findViewById(R.id.edit_airplane_active);

        modelView.setText(editModel);
        manufacturingDateView.setText(editManufacturingDate);
        passengerCapacityView.setText(String.valueOf(editPassengerCapacity));
        maxSpeedView.setText(String.valueOf(editMaxSpeed));
        airlineIdView.setText(String.valueOf(editAirlineId));
        checkActive.setChecked(isActive);

    }

    public void editOneAirplane(View view) {

        Snackbar snackbar = Snackbar.make(view, R.string.question_edit, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.question_edit_button, view1 -> {

                    EditText editModel = findViewById(R.id.edit_airplane_model);
                    EditText editManufacturingDate = findViewById(R.id.edit_airplane_manufacturing_date);
                    EditText editPassengerCapacity = findViewById(R.id.edit_airplane_passenger_capacity);
                    EditText editMaxSpeed = findViewById(R.id.edit_airplane_max_speed);
                    EditText editAirplaneId = findViewById(R.id.edit_airplane_airline_id);
                    CheckBox checkActive = findViewById(R.id.edit_airplane_active);

                    if (ValidatorUtil.areEditTextsValid(editModel, editManufacturingDate, editPassengerCapacity, editMaxSpeed, editAirplaneId)) {
                        String model = editModel.getText().toString();
                        String manufacturingDate = editManufacturingDate.getText().toString();
                        int passengerCapacity = Integer.parseInt(editPassengerCapacity.getText().toString());
                        float maxSpeed = Float.parseFloat(editMaxSpeed.getText().toString());
                        long airlineId = Long.parseLong(editAirplaneId.getText().toString());
                        Airline airline = new Airline(airlineId);

                        boolean active = checkActive.isChecked();

                        Airplane airplane = new Airplane(0, model, manufacturingDate, passengerCapacity, maxSpeed, active, airline);

                        editPresenter.editOneAirplane(airplaneId, airplane);

                    } else {
                        showMessage(R.string.field_incomplete);
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light));
        snackbar.show();
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
                    Intent intent = new Intent(this, AirplaneListView.class);
                    startActivity(intent);
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_blue_light));
        snackbar.show();
    }
}
