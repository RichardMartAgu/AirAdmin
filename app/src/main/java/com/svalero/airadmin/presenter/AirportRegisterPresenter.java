package com.svalero.airadmin.presenter;

import android.content.Context;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.AirportRegisterContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.AirportRegisterModel;

public class AirportRegisterPresenter implements AirportRegisterContract.Presenter, AirportRegisterContract.Model.OnRegisterAirportListener {

    private AirportRegisterContract.View view;
    private AirportRegisterContract.Model model;

    public AirportRegisterPresenter(AirportRegisterContract.View view ,Context context) {
        this.view = view;
        model = new AirportRegisterModel(context);
    }

    @Override
    public void registerAirport(Airport airport) {
        model.registerAirport(this, airport);
    }

    @Override
    public void onRegisterAirportSuccess() {
        view.showMessage(R.string.registerOk);
    }

    @Override
    public void onRegisterAirportError(String message) {
        view.showMessage(message);
    }

}