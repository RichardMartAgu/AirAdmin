package com.svalero.airadmin.view.airplanesViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDeleteContract;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDetailsContract;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneEditContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneDeletePresenter;
import com.svalero.airadmin.presenter.airplanesPresenters.AirplaneDetailsPresenter;

public class AirplaneDetailsView extends AppCompatActivity implements  AirplaneDetailsContract.View, AirplaneDeleteContract.View, AirplaneEditContract.View {

    private long airplaneId;
    private boolean airplaneFavorite;

    AirplaneDeleteContract.Presenter deletePresenter = new AirplaneDeletePresenter(this);
    AirplaneDetailsContract.Presenter detailsPresenter = new AirplaneDetailsPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane_details);
        Intent intent = getIntent();
        airplaneId = getIntent().getLongExtra("airplane_item_id", 2);
        airplaneFavorite = intent.getBooleanExtra("airplane_fav_btn", false);
        detailsPresenter.loadOneAirplane(airplaneId);

    }

    public void listOneAirplane(Airplane airplane) {

        TextView airplaneIdView = findViewById(R.id.airplane_details_id);
        TextView airplaneModel = findViewById(R.id.airplane_details_model);
        TextView airplaneManufacturingDate = findViewById(R.id.airplane_details_manufacturing_date);
        TextView airplanePassengerCapacity = findViewById(R.id.airplane_details_passenger_capacity);
        TextView airplaneMaxSpeed = findViewById(R.id.airplane_details_max_speed);

        CheckBox active = findViewById(R.id.airplane_details_active);
        ImageButton marked = findViewById(R.id.airplane_details_fav_btn);


        String airplaneIdText = String.valueOf(airplaneId);
        airplaneIdView.setText(airplaneIdText);
        airplaneModel.setText(airplane.getModel());
        airplaneManufacturingDate.setText(airplane.getManufacturingDate());
        airplaneIdView.setText(airplaneIdText);
        airplanePassengerCapacity.setText(String.valueOf(airplane.getPassengerCapacity()));
        airplaneMaxSpeed.setText(String.valueOf(airplane.getMaxSpeed()));

        active.setChecked(airplane.isActive());

        if (airplaneFavorite) {
            marked.setImageResource(android.R.drawable.star_big_on);
            marked.setSelected(true);
        } else {
            marked.setImageResource(android.R.drawable.star_big_off);
            marked.setSelected(false);
        }
    }

    public void deleteOneAirplane(View view) {
        deletePresenter.deleteOneAirplane(airplaneId);
        showMessage("Avión eliminado exitosamente");
        Intent intent = new Intent(AirplaneDetailsView.this, AirplaneListView.class);
        startActivity(intent);

    }

    public void goEditOneAirplane(View view) {

        String airplaneId = ((TextView) findViewById(R.id.airplane_details_id)).getText().toString();
        String model = ((TextView) findViewById(R.id.airplane_details_model)).getText().toString();
        String manufacturingDate = ((TextView) findViewById(R.id.airplane_details_manufacturing_date)).getText().toString();
        String passengerCapacity = ((TextView) findViewById(R.id.airplane_details_passenger_capacity)).getText().toString();
        String maxSpeed = ((TextView) findViewById(R.id.airplane_details_max_speed)).getText().toString();
        CheckBox active = findViewById(R.id.airplane_details_active);
        boolean isActive = active.isChecked();
        ImageButton marked = findViewById(R.id.airplane_details_fav_btn);
        boolean isMarked = marked.isSelected();


        Intent intent = new Intent(this, AirplaneEditView.class);

        intent.putExtra("airplane_details_id", airplaneId);
        intent.putExtra("airplane_details_model", model);
        intent.putExtra("airplane_details_manufacturing_date", manufacturingDate);
        intent.putExtra("airplane_details_passenger_capacity", passengerCapacity);
        intent.putExtra("airplane_details_max_speed", maxSpeed);

        intent.putExtra("airplane_details_active", isActive);
        intent.putExtra("airplane_details_fav_btn", isMarked);

        view.getContext().startActivity(intent);

    }

    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showMessage(Airplane airplane) {

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
