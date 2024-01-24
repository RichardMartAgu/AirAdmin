package com.svalero.airadmin.presenter.airportsPresenters;

import com.svalero.airadmin.contract.airportsContracts.AirportDetailsContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.airportsModel.AirportDetailsModel;

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
    public void onLoadOneAirportError(int stringId) {
        view.showMessage(stringId);
    }

}