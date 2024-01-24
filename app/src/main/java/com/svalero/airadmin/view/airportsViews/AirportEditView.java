package com.svalero.airadmin.view.airportsViews;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
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

        Point point = (Point.fromLngLat(-4.25, 41.29));
        setCameraPosition(point);

    }

    public void editOneAirport(View view) {
        Snackbar snackbar = Snackbar.make(view, "Seguro que quiere editar el registro?", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Editar definitivamente", view1 -> {

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
                        double longitude = Double.parseDouble(editLongitude.getText().toString());
                        double latitude = Double.parseDouble(editLatitude.getText().toString());

                        boolean active = checkActive.isChecked();

                        Airport airport = new Airport(0, name, city, foundationYear, latitude, longitude, active);
                        editPresenter.editOneAirport(airportId, airport);

                    } else {
                        showMessage("Por favor, completa todos los campos");
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light));
        snackbar.show();

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
        EditText airportLatitude = findViewById(R.id.edit_airport_latitude);
        airportLatitude.setText(String.valueOf(point.latitude()));
        EditText airportLongitude = findViewById(R.id.edit_airport_longitude);
        airportLongitude.setText(String.valueOf(point.longitude()));

        return false;
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
    public void onStyleLoaded(@NonNull Style style) {

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
