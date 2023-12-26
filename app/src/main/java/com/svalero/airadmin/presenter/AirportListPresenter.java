package com.svalero.airadmin.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.svalero.airadmin.contract.AirportListContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.AirportListModel;

import java.util.List;

public class AirportListPresenter implements AirportListContract.Presenter, AirportListContract.Model.OnLoadAirportListener {

    private AirportListContract.View view;
    private AirportListContract.Model model;

    public AirportListPresenter(AirportListContract.View view) {
        this.view = view;
        model = new AirportListModel();
    }
    @Override
    public void loadAllAirports() {
        model.loadAllAirports(this);
    }

    @Override
    public void onLoadAirportSuccess(List<Airport> airport) {
        view.listAirports(airport);
    }

    @Override
    public void onLoadAirportError(String message) {
        view.showMessage(message);
    }



}

