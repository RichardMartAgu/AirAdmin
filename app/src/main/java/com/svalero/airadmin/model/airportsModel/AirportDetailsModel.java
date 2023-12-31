package com.svalero.airadmin.model.airportsModel;

import android.util.Log;

import com.svalero.airadmin.api.AirportApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.airportsContracts.AirportDetailsContract;
import com.svalero.airadmin.domain.Airport;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
                    String errorMessage = "Error en la respuesta";
                    if (response.errorBody() != null) {
                        try {

                            JSONObject errorJson = new JSONObject(response.errorBody().string());

                            errorMessage = errorJson.optString("message", errorMessage);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e("getAirportById", "Error en la respuesta: " + errorMessage);
                    listener.onLoadOneAirportError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                Log.e("getAirportById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadOneAirportError("Se ha producido un error al conectar con el servidor");
            }
        });
    }


}
