package com.svalero.airadmin.model.airplanesModels;

import android.util.Log;

import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDeleteContract;
import com.svalero.airadmin.domain.Airplane;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirplaneDeleteModel implements AirplaneDeleteContract.Model {


    @Override
    public void loadDeleteOneAirplane(long airplaneId, OnLoadDeleteOneAirplaneListener listener) {
        AirplaneInterface api = AirplaneApi.buildInstance();
        Call<Airplane> deleteAirplaneCall = api.deleteAirplaneById(airplaneId);
        deleteAirplaneCall.enqueue(new Callback<Airplane>() {
            @Override
            public void onResponse(Call<Airplane> call, Response<Airplane> response) {
                if (response.isSuccessful()) {
                    listener.onLoadDeleteOneAirplaneSuccess();

                } else {
                    String errorMessage = response.message();
                    Log.e("deleteAirplaneById", "Error en la respuesta: " + errorMessage);
                    listener.onLoadDeleteOneAirplaneError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airplane> call, Throwable t) {
                Log.e("deleteAirplaneById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadDeleteOneAirplaneError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
