package com.svalero.airadmin.presenter.airplanesPresenters;

import android.content.Context;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneEditContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.model.airplanesModels.AirplaneEditModel;

public class AirplaneEditPresenter implements AirplaneEditContract.Presenter, AirplaneEditContract.Model.OnLoadEditOneAirplaneListener {

    private AirplaneEditContract.View view;
    private AirplaneEditContract.Model model;

    public AirplaneEditPresenter(AirplaneEditContract.View view, Context context) {
        this.view = view;
        this.model = new AirplaneEditModel(context);
    }

    @Override
    public void onLoadEditOneAirplaneSuccess() {
        view.showMessage(R.string.edit_ok);
    }

    @Override
    public void onLoadEditOneAirplaneError(int stringId) {
        view.showMessage(stringId);
    }

    @Override
    public void editOneAirplane(long airplaneId, Airplane airplane) {
        model.loadEditOneAirplane(airplaneId, airplane, this);
    }
}
