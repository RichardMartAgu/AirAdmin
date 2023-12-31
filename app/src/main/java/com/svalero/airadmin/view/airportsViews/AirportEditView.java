package com.svalero.airadmin.view.airportsViews;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airportsContracts.AirportEditContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.presenter.airportsPresenters.AirportEditPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;

import java.util.Objects;

public class AirportEditView extends AppCompatActivity implements Style.OnStyleLoaded,
        OnMapClickListener, AirportEditContract.View {
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;
    private long airportId;
    private String editName;
    private String editCity;
    private String editFoundationYear;
    double editLongitude;
    double editLatitude;
    private AirportEditContract.Presenter editPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_edit);
        editPresenter = new AirportEditPresenter(this, this);
        Intent intent = getIntent();

        airportId = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("airport_details_id")));
        editName = intent.getStringExtra("airport_details_name");
        editCity = intent.getStringExtra("airport_details_city");
        editFoundationYear = intent.getStringExtra("airport_details_foundation_year");
        editLongitude = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("airport_details_latitude")));
        editLatitude = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("airport_details_longitude")));
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

        mapView = findViewById(R.id.edit_mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);

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
            String latitudeText = editLongitude.getText().toString();
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
            String longitudeText = editLatitude.getText().toString();
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

            Airport airport = new Airport(0, name,city, foundationYear, latitude, longitude, active);

            editPresenter.editOneAirport(airportId, airport);

            Intent intent = new Intent(this, AirportListView.class);
            startActivity(intent);


        } else {
            showMessage("Por favor, completa todos los campos");
        }

    }
    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }
    private void addMarker(double latitude, double longitude, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withTextField(title);
        pointAnnotationManager.create(pointAnnotationOptions);
    }
    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude(), getString(R.string.here));
        EditText airportLatitude = findViewById(R.id.edit_airport_longitude);
        airportLatitude.setText(String.valueOf(point.latitude()));
        EditText airportLongitude = findViewById(R.id.edit_airport_latitude);
        airportLongitude.setText(String.valueOf(point.longitude()));

        return false;
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

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
