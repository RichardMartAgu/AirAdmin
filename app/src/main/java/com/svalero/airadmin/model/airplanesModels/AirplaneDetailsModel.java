package com.svalero.airadmin.model.airplanesModels;

import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDetailsContract;
import com.svalero.airadmin.domain.Airplane;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirplaneDetailsModel implements AirplaneDetailsContract.Model {

    public void loadOneAirplane(long airplaneId, OnLoadOneAirplaneListener listener) {
        AirplaneInterface api = AirplaneApi.buildInstance();
        Call<Airplane> getAirplaneCall = api.getAirplaneById(airplaneId);
        getAirplaneCall.enqueue(new Callback<Airplane>() {
            @Override
            public void onResponse(Call<Airplane> call, Response<Airplane> response) {
                if (response.isSuccessful()) {
                    Airplane airplane = response.body();
                    listener.onLoadOneAirplaneSuccess(airplane);
                } else {
                    Log.e("getAirplaneById", "Error al buscar por Id");
                    listener.onLoadOneAirplaneError(R.string.error_search_by_id);
                }
            }

            @Override
            public void onFailure(Call<Airplane> call, Throwable t) {
                Log.e("getAirportById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadOneAirplaneError(R.string.error_server);
            }
        });
    }


}
