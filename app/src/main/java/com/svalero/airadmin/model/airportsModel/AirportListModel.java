package com.svalero.airadmin.model.airportsModel;

import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirportApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.airportsContracts.AirportListContract;
import com.svalero.airadmin.domain.Airport;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportListModel implements AirportListContract.Model {

    @Override
    public void loadAllAirports(AirportListContract.Model.OnLoadAllAirportListener listener) {
        AirportInterface api = AirportApi.buildInstance();
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
                listener.onLoadAllAirportError(R.string.error_server);
            }
        });
    }
}
