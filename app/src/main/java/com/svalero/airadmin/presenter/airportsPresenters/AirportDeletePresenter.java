package com.svalero.airadmin.presenter.airportsPresenters;

import com.svalero.airadmin.contract.airportsContracts.AirportDeleteContract;
import com.svalero.airadmin.model.airportsModel.AirportDeleteModel;

public class AirportDeletePresenter implements AirportDeleteContract.Presenter, AirportDeleteContract.Model.OnLoadDeleteOneAirportListener {
    private AirportDeleteContract.View view;
    private AirportDeleteContract.Model model;
    public AirportDeletePresenter(AirportDeleteContract.View view) {
        this.view = view;
        this.model = new AirportDeleteModel();
    }
    @Override
    public void onLoadDeleteOneAirportSuccess() {
    }

    @Override
    public void onLoadDeleteOneAirportError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteOneAirport(long airportId) {
        model.loadDeleteOneAirport(airportId,this);
    }
}
