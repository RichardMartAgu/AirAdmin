package com.svalero.airadmin.model.airplanesModels;

import android.util.Log;

import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneDetailsContract;
import com.svalero.airadmin.domain.Airplane;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
                    String errorMessage = "Error en la respuesta";
                    if (response.errorBody() != null) {
                        try {

                            JSONObject errorJson = new JSONObject(response.errorBody().string());

                            errorMessage = errorJson.optString("message", errorMessage);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e("getAirplaneById", "Error en la respuesta: " + errorMessage);
                    listener.onLoadOneAirplaneError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airplane> call, Throwable t) {
                Log.e("getAirportById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadOneAirplaneError("Se ha producido un error al conectar con el servidor");
            }
        });
    }


}
