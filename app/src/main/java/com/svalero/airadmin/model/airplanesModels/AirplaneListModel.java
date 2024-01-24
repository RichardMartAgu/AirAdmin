package com.svalero.airadmin.model.airplanesModels;

import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirportListContract;
import com.svalero.airadmin.domain.Airplane;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirplaneListModel implements AirportListContract.Model {

    @Override
    public void loadAllAirplane(AirportListContract.Model.OnLoadAllAirplaneListener listener) {
        AirplaneInterface api = AirplaneApi.buildInstance();
        Call<List<Airplane>> getAirportCall = api.getAirplanes();
        getAirportCall.enqueue(new Callback<List<Airplane>>() {
            @Override
            public void onResponse(Call<List<Airplane>> call, Response<List<Airplane>> response) {
                Log.e("getAirplanes", response.message());
                List<Airplane> airplanes = response.body();
                listener.onLoadAllAirplaneSuccess(airplanes);
            }

            @Override
            public void onFailure(Call<List<Airplane>> call, Throwable t) {
                Log.e("getAirplanes", t.getMessage());
                listener.onLoadAllAirplaneError(R.string.error_server);
            }
        });
    }
}
