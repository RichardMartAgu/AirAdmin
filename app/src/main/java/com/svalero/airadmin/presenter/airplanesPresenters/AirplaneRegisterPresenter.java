package com.svalero.airadmin.presenter.airplanesPresenters;

import android.content.Context;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneRegisterContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.model.airplanesModels.AirplaneRegisterModel;

public class AirplaneRegisterPresenter implements AirplaneRegisterContract.Presenter, AirplaneRegisterContract.Model.OnRegisterAirplaneListener {

    private AirplaneRegisterContract.View view;
    private AirplaneRegisterContract.Model model;

    public AirplaneRegisterPresenter(AirplaneRegisterContract.View view, Context context) {
        this.view = view;
        model = new AirplaneRegisterModel(context);
    }

    @Override
    public void registerAirplane(Airplane airplane) {
        model.registerAirplane(this, airplane);
    }

    @Override
    public void onRegisterAirplaneSuccess() {
        view.showMessage(R.string.register_ok);
    }

    @Override
    public void onRegisterAirplaneError(int stringId) {
        view.showMessage(stringId);
    }


}