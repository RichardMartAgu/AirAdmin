package com.svalero.airadmin.model.airportsModel;

import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirportApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.airportsContracts.AirportDetailsContract;
import com.svalero.airadmin.domain.Airport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportDetailsModel implements AirportDetailsContract.Model {

    public void loadOneAirport(long airportId, AirportDetailsContract.Model.OnLoadOneAirportListener listener) {
        AirportInterface api = AirportApi.buildInstance();
        Call<Airport> getAirportCall = api.getAirportById(airportId);
        getAirportCall.enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.isSuccessful()) {
                    Airport airport = response.body();
                    listener.onLoadOneAirportSuccess(airport);
                } else {
                    Log.e("getAirportById", "Error al buscar por id");
                    listener.onLoadOneAirportError(R.string.error_search_by_id);
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                Log.e("getAirportById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadOneAirportError(R.string.error_server);
            }
        });
    }


}
