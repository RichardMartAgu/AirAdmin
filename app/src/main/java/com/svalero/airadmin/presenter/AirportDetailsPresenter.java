package com.svalero.airadmin.presenter;

import com.svalero.airadmin.contract.AirportDetailsContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.AirportDetailsModel;


import java.util.List;

import retrofit2.Callback;

public class AirportDetailsPresenter implements AirportDetailsContract.Presenter, AirportDetailsContract.Model.OnLoadOneAirportListener {

    private AirportDetailsContract.View view;
    private AirportDetailsContract.Model model;

    public AirportDetailsPresenter(AirportDetailsContract.View view) {
        this.view = view;
        model = new AirportDetailsModel();
    }
    @Override
    public void loadOneAirport(long airportId) {
        model.loadOneAirport(airportId, this);
    }

    @Override
    public void onLoadOneAirportSuccess(Airport airport) {
        view.listOneAirport(airport);
    }

    @Override
    public void onLoadOneAirportError(String message) {
        view.showMessage(message);
    }

}