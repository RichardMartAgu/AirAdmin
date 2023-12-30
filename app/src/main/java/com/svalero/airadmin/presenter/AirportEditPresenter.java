package com.svalero.airadmin.presenter;

import android.content.Context;
import com.svalero.airadmin.contract.AirportEditContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.AirportEditModel;

public class AirportEditPresenter implements AirportEditContract.Presenter, AirportEditContract.Model.OnLoadEditOneAirportListener {

    private AirportEditContract.View view;
    private AirportEditContract.Model model;

    public AirportEditPresenter(AirportEditContract.View view, Context context) {
        this.view = view;
        this.model = new AirportEditModel(context);
    }
    @Override
    public void onLoadEditOneAirportSuccess() {
    }

    @Override
    public void onLoadEditOneAirportError(String message) {
        view.showMessage(message);
    }

    @Override
    public void editOneAirport(long airportId, Airport airport) {
        model.loadEditOneAirport(airportId,airport,this);
    }
}
