package com.svalero.airadmin.model;

import android.util.Log;

import com.svalero.airadmin.api.AirApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.AirportDeleteContract;
import com.svalero.airadmin.domain.Airport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportDeleteModel implements AirportDeleteContract.Model {


    @Override
    public void loadDeleteOneAirport(long airportId, OnLoadDeleteOneAirportListener listener) {
        AirportInterface api = AirApi.buildInstance();
        Call<Airport> deleteAirportCall = api.deleteAirportById(airportId);
        deleteAirportCall.enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.isSuccessful()) {
                    listener.onLoadDeleteOneAirportSuccess();

                } else {
                    String errorMessage = response.message();
                    Log.e("deleteAirportById", "Error en la respuesta: " + errorMessage);
                    listener.onLoadDeleteOneAirportError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                Log.e("deleteAirportById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadDeleteOneAirportError("Se ha producido un error al conectar con el servidor");
            }
        });
    }
}
