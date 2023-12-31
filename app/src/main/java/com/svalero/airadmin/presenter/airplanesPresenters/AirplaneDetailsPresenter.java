package com.svalero.airadmin.presenter.airplanesPresenters;

import com.svalero.airadmin.contract.airplanesContracts.AirplaneDetailsContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.model.airplanesModels.AirplaneDetailsModel;

public class AirplaneDetailsPresenter implements AirplaneDetailsContract.Presenter, AirplaneDetailsContract.Model.OnLoadOneAirplaneListener {

    private AirplaneDetailsContract.View view;
    private AirplaneDetailsContract.Model model;

    public AirplaneDetailsPresenter(AirplaneDetailsContract.View view) {
        this.view = view;
        model = new AirplaneDetailsModel();
    }
    @Override
    public void loadOneAirplane(long airplaneId) {
        model.loadOneAirplane(airplaneId, this);
    }

    @Override
    public void onLoadOneAirplaneSuccess(Airplane airplane) {
        view.listOneAirplane(airplane);
    }

    @Override
    public void onLoadOneAirplaneError(String message) {
        view.showMessage(message);
    }

}