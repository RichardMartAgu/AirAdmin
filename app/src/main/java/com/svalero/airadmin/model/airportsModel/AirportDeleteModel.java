package com.svalero.airadmin.model.airportsModel;

import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirportApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.airportsContracts.AirportDeleteContract;
import com.svalero.airadmin.domain.Airport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportDeleteModel implements AirportDeleteContract.Model {


    @Override
    public void loadDeleteOneAirport(long airportId, OnLoadDeleteOneAirportListener listener) {
        AirportInterface api = AirportApi.buildInstance();
        Call<Airport> deleteAirportCall = api.deleteAirportById(airportId);
        deleteAirportCall.enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.isSuccessful()) {
                    listener.onLoadDeleteOneAirportSuccess();

                } else {
                    Log.e("deleteAirportById", "Error al borrar ");
                    listener.onLoadDeleteOneAirportError(R.string.error_to_delete);
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                Log.e("deleteAirportById", "Error en la solicitud: " + t.getMessage());
                listener.onLoadDeleteOneAirportError(R.string.error_server);
            }
        });
    }
}
