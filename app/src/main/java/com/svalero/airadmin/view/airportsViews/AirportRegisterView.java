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
import com.mapbox.maps.CameraOptions;
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
import com.svalero.airadmin.contract.airportsContracts.AirportRegisterContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.presenter.airportsPresenters.AirportRegisterPresenter;
import com.svalero.airadmin.utils.ValidatorUtil;

public class AirportRegisterView extends AppCompatActivity implements Style.OnStyleLoaded,
        OnMapClickListener, AirportRegisterContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;
    private AirportRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_register);
        presenter = new AirportRegisterPresenter(this, this);

        mapView = findViewById(R.id.add_mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        initializePointAnnotationManager();

        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);

        Point point = (Point.fromLngLat(-4.25,41.29));
        setCameraPosition(point);
    }

    public void createAirport(View view) {
        EditText addName = findViewById(R.id.add_airport_name);
        EditText addCity = findViewById(R.id.add_airport_city);
        EditText addFoundationYear = findViewById(R.id.add_airport_foundation_year);
        EditText addLongitude = findViewById(R.id.add_airport_longitude);
        EditText addLatitude = findViewById(R.id.add_airport_latitude);
        CheckBox checkActive = findViewById(R.id.add_active);

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
        EditText airportLatitude = findViewById(R.id.add_airport_longitude);
        airportLatitude.setText(String.valueOf(point.latitude()));
        EditText airportLongitude = findViewById(R.id.add_airport_latitude);
        airportLongitude.setText(String.valueOf(point.longitude()));
        return false;
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }
    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(5.0)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
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