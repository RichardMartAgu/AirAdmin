package com.svalero.airadmin.presenter.airportsPresenters;


import com.svalero.airadmin.contract.airportsContracts.AirportListContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.airportsModel.AirportListModel;

import java.util.List;

public class AirportListPresenter implements com.svalero.airadmin.contract.airportsContracts.AirportListContract.Presenter, com.svalero.airadmin.contract.airportsContracts.AirportListContract.Model.OnLoadAllAirportListener {

    private AirportListContract.View view;
    private AirportListContract.Model model;

    public AirportListPresenter(AirportListContract.View view) {
        this.view = view;
        model = new AirportListModel();
    }


    @Override
    public void onLoadAllAirportSuccess(List<Airport> airports) {
        view.listAirports(airports);
    }

    @Override
    public void onLoadAllAirportError(int StringId){
        view.showMessage(StringId);
    }


    @Override
    public void loadAllAirports() {
        model.loadAllAirports(this);
    }
}

