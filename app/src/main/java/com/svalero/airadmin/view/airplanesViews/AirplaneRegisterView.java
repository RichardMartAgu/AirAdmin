package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneRegisterContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneRegisterPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;

public class AirplaneRegisterView extends AppCompatActivity implements  AirplaneRegisterContract.View {

    private AirplaneRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_register);
        presenter = new AirplaneRegisterPresenter(this, this);

    }


    public void createAirport(View view) {
        EditText addModel = findViewById(R.id.add_airplane_model);
        EditText addManufacturingDate = findViewById(R.id.add_airplane_manufacturing_date);
        EditText addPassengerCapacity = findViewById(R.id.add_airplane_passenger_capacity);
        EditText addMaxSpeed = findViewById(R.id.add_airplane_max_speed);
        CheckBox checkActive = findViewById(R.id.add_airplane_active);

        if (ValidatorUtil.areEditTextsValid(addModel, addManufacturingDate, addPassengerCapacity, addMaxSpeed)) {
            String model = addModel.getText().toString();
            String manufacturingDate = addManufacturingDate.getText().toString();
            int passengerCapacity = Integer.parseInt(addPassengerCapacity.getText().toString());
            float maxSpeed = Float.parseFloat(addMaxSpeed.getText().toString());

            boolean active = checkActive.isChecked();

            Airplane airplane = new Airplane(0, model,manufacturingDate, passengerCapacity, maxSpeed, active);
            presenter.registerAirplane(airplane);

            Intent intent = new Intent(this, AirplaneListView.class);
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