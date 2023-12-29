package com.svalero.airadmin.presenter;

import com.svalero.airadmin.contract.AirportListContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.AirportListModel;

import java.util.List;

public class AirportListPresenter implements AirportListContract.Presenter, AirportListContract.Model.OnLoadAllAirportListener {

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
    public void onLoadAllAirportSuccess(List<Airport> airport) {
        view.listAllAirports(airport);
    }

    @Override
    public void onLoadAllAirportError(String message) {
        view.showMessage(message);
    }



}

