package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneEditContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneEditPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;

import java.util.Objects;

public class AirplaneEditView extends AppCompatActivity implements  AirplaneEditContract.View {


    private long airplaneId;
    private String editModel;
    private String editManufacturingDate;
    private int adeitPassengerCapacity;
    
    private float adetMaxSpeed;
    
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
        adeitPassengerCapacity = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("airplane_details_passenger_capacity")));
        adetMaxSpeed = Float.parseFloat(Objects.requireNonNull(intent.getStringExtra("airplane_details_max_speed")));

        boolean isActive = intent.getBooleanExtra("airplane_details_active", false);

        EditText modelView = findViewById(R.id.edit_airplane_model);
        EditText manufacturingDateView = findViewById(R.id.edit_airplane_manufacturing_date);
        EditText passengerCapacityView = findViewById(R.id.edit_airplane_passenger_capacity);
        EditText maxSpeedView = findViewById(R.id.edit_airplane_max_speed);
        CheckBox checkActive = findViewById(R.id.edit_airplane_active);

        modelView.setText(editModel);
        manufacturingDateView.setText(editManufacturingDate);
        passengerCapacityView.setText(String.valueOf(adeitPassengerCapacity));
        maxSpeedView.setText(String.valueOf(adetMaxSpeed));
        checkActive.setChecked(isActive);


    }


    public void editOneAirplane(View view) {

        EditText editModel = findViewById(R.id.edit_airplane_model);
        EditText editManufacturingDate= findViewById(R.id.edit_airplane_manufacturing_date);
        EditText editPassengerCapacity = findViewById(R.id.edit_airplane_passenger_capacity);
        EditText editMaxSpeed = findViewById(R.id.edit_airplane_max_speed);
        CheckBox checkActive = findViewById(R.id.edit_airplane_active);

        if (ValidatorUtil.areEditTextsValid(editModel, editManufacturingDate, editPassengerCapacity, editMaxSpeed)) {
            String model = editModel.getText().toString();
            String manufacturingDate = editManufacturingDate.getText().toString();
            int passengerCapacity = Integer.parseInt(editPassengerCapacity.getText().toString());
            float maxSpeed = Float.parseFloat(editMaxSpeed.getText().toString());
            

            boolean active = checkActive.isChecked();

            Airplane airplane = new Airplane(0, model, manufacturingDate, passengerCapacity, maxSpeed, active);

            editPresenter.editOneAirplane(airplaneId, airplane);

            Intent intent = new Intent(this, AirplaneListView.class);
            startActivity(intent);


        } else {
            showMessage("Por favor, completa todos los campos");
        }

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        //TODO revisar los mensajes
    }

    @Override
    public void showMessage(int stringId) {
        showMessage(getResources().getString(stringId));
    }
}
