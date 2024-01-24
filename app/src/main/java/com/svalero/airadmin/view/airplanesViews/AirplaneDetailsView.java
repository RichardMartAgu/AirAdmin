package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDeleteContract;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDetailsContract;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneEditContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneDeletePresenter;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneDetailsPresenter;

public class AirplaneDetailsView extends AppCompatActivity implements AirplaneDetailsContract.View, AirplaneDeleteContract.View, AirplaneEditContract.View {

    private long airplaneId;

    AirplaneDeleteContract.Presenter deletePresenter = new AirplaneDeletePresenter(this);
    AirplaneDetailsContract.Presenter detailsPresenter = new AirplaneDetailsPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_details);
        airplaneId = getIntent().getLongExtra("airplane_item_id", 2);
        detailsPresenter.loadOneAirplane(airplaneId);

    }

    public void listOneAirplane(Airplane airplane) {

        TextView airplaneIdView = findViewById(R.id.airplane_details_id);
        TextView airplaneModel = findViewById(R.id.airplane_details_model);
        TextView airplaneManufacturingDate = findViewById(R.id.airplane_details_manufacturing_date);
        TextView airplanePassengerCapacity = findViewById(R.id.airplane_details_passenger_capacity);
        TextView airplaneMaxSpeed = findViewById(R.id.airplane_details_max_speed);
        TextView airplaneAirportId = findViewById(R.id.airplane_details_airline_id);

        CheckBox active = findViewById(R.id.airplane_details_active);

        String airplaneIdText = String.valueOf(airplaneId);
        airplaneIdView.setText(airplaneIdText);
        airplaneModel.setText(airplane.getModel());
        airplaneManufacturingDate.setText(airplane.getManufacturingDate());
        airplanePassengerCapacity.setText(String.valueOf(airplane.getPassengerCapacity()));
        airplaneMaxSpeed.setText(String.valueOf(airplane.getMaxSpeed()));
        airplaneAirportId.setText(String.valueOf(airplane.getAirline().getId()));

        active.setChecked(airplane.isActive());

    }


    public void deleteOneAirplane(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.question_delete, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.question_delete_button, view1 -> {
                    deletePresenter.deleteOneAirplane(airplaneId);
                    Intent intent = new Intent(AirplaneDetailsView.this, AirplaneListView.class);
                    String message = getString(R.string.airplane_delete_sucefull);
                    intent.putExtra("Snackbar",message );
                    startActivity(intent);
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light));
        snackbar.show();
    }

    public void goEditOneAirplane(View view) {

        String airplaneId = ((TextView) findViewById(R.id.airplane_details_id)).getText().toString();
        String model = ((TextView) findViewById(R.id.airplane_details_model)).getText().toString();
        String manufacturingDate = ((TextView) findViewById(R.id.airplane_details_manufacturing_date)).getText().toString();
        String passengerCapacity = ((TextView) findViewById(R.id.airplane_details_passenger_capacity)).getText().toString();
        String maxSpeed = ((TextView) findViewById(R.id.airplane_details_max_speed)).getText().toString();
        String airlineId = ((TextView) findViewById(R.id.airplane_details_airline_id)).getText().toString();
        CheckBox active = findViewById(R.id.airplane_details_active);
        boolean isActive = active.isChecked();

        Intent intent = new Intent(this, AirplaneEditView.class);

        intent.putExtra("airplane_details_id", airplaneId);
        intent.putExtra("airplane_details_model", model);
        intent.putExtra("airplane_details_manufacturing_date", manufacturingDate);
        intent.putExtra("airplane_details_passenger_capacity", passengerCapacity);
        intent.putExtra("airplane_details_max_speed", maxSpeed);
        intent.putExtra("airplane_details_airline_id", airlineId);

        intent.putExtra("airplane_details_active", isActive);

        view.getContext().startActivity(intent);

    }

    @Override
    public void showMessage(String message) {
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showMessage(int stringId) {
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar.make(view, stringId, Snackbar.LENGTH_SHORT).show();


    }
}
