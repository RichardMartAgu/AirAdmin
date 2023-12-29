package com.svalero.airadmin.model;

import android.util.Log;

import com.svalero.airadmin.api.AirApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.AirportListContract;
import com.svalero.airadmin.domain.Airport;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportListModel implements AirportListContract.Model {

    @Override
    public void loadAllAirports(OnLoadAllAirportListener listener) {
        AirportInterface api = AirApi.buildInstance();
        Call<List<Airport>> getAirportCall = api.getAirports();
        getAirportCall.enqueue(new Callback<List<Airport>>() {
            @Override
            public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
                Log.e("getAirports", response.message());
                List<Airport> airports = response.body();
                listener.onLoadAllAirportSuccess(airports);
            }

            @Override
            public void onFailure(Call<List<Airport>> call, Throwable t) {
                Log.e("getAirports", t.getMessage());
                listener.onLoadAllAirportError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
