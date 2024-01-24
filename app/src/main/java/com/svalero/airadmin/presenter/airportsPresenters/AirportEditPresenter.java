package com.svalero.airadmin.presenter.airportsPresenters;

import android.content.Context;

import com.svalero.airadmin.R;
import com.svalero.airadmin.contract.airportsContracts.AirportEditContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.model.airportsModel.AirportEditModel;

public class AirportEditPresenter implements AirportEditContract.Presenter, AirportEditContract.Model.OnLoadEditOneAirportListener {

    private AirportEditContract.View view;
    private AirportEditContract.Model model;

    public AirportEditPresenter(AirportEditContract.View view, Context context) {
        this.view = view;
        this.model = new AirportEditModel(context);
    }

    @Override
    public void onLoadEditOneAirportSuccess() {
        view.showMessage(R.string.edit_ok);
    }

    @Override
    public void onLoadEditOneAirportError(int StringId) {
        view.showMessage(StringId);
    }

    @Override
    public void editOneAirport(long airportId, Airport airport) {
        model.loadEditOneAirport(airportId, airport, this);
    }
}
