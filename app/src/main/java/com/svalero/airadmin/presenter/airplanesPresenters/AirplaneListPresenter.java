package com.svalero.airadmin.presenter.airplanesPresenters;

import com.svalero.airadmin.contract.airplanesContracts.AirportListContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.model.airplanesModels.AirplaneListModel;

import java.util.List;

public class AirplaneListPresenter implements AirportListContract.Presenter, AirportListContract.Model.OnLoadAllAirplaneListener {

    private AirportListContract.View view;
    private AirportListContract.Model model;

    public AirplaneListPresenter(AirportListContract.View view) {
        this.view = view;
        model = new AirplaneListModel();
    }


    @Override
    public void onLoadAllAirplaneSuccess(List<Airplane> airplane) {
        view.listAirplane(airplane);
    }

    @Override
    public void onLoadAllAirplaneError(String message) {
        view.showMessage(message);
    }


    @Override
    public void loadAllAirplanes() {
        model.loadAllAirplane(this);
    }
}

