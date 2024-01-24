package com.svalero.airadmin.presenter.airplanesPresenters;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDeleteContract;
import com.svalero.airadmin.model.airplanesModels.AirplaneDeleteModel;

public class AirplaneDeletePresenter implements AirplaneDeleteContract.Presenter, AirplaneDeleteContract.Model.OnLoadDeleteOneAirplaneListener {
    private AirplaneDeleteContract.View view;
    private AirplaneDeleteContract.Model model;

    public AirplaneDeletePresenter(AirplaneDeleteContract.View view) {
        this.view = view;
        this.model = new AirplaneDeleteModel();
    }

    @Override
    public void onLoadDeleteOneAirplaneSuccess() {

    }

    @Override
    public void onLoadDeleteOneAirplaneError(int stringId) {
        view.showMessage(stringId);
    }

    @Override
    public void deleteOneAirplane(long airplaneId) {
        model.loadDeleteOneAirplane(airplaneId, this);
    }
}
