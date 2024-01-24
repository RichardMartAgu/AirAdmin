package com.svalero.airadmin.model.airportsModel;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CREATED;

import android.content.Context;
import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirportApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.airportsContracts.AirportRegisterContract;
import com.svalero.airadmin.domain.Airport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportRegisterModel implements AirportRegisterContract.Model {
    private Context context;

    public AirportRegisterModel(Context context) {
        this.context = context;
    }

    public void registerAirport(OnRegisterAirportListener listener, Airport airport) {
        AirportInterface api = AirportApi.buildInstance();
        Call<Airport> addAirportCall = api.addAirport(airport);
        Log.d("AirportRegisterModel", "Value of 'active' in model: " + airport.isActive());
        addAirportCall.enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.code() == HTTP_CREATED) {
                    Log.e("addAirport", response.message());
                    listener.onRegisterAirportSuccess();

                } else if (response.code() == HTTP_BAD_REQUEST) {
                    listener.onRegisterAirportError(R.string.error_validation);
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                Log.e("addAirport", "Error en la solicitud: " + t.getMessage());
                listener.onRegisterAirportError(R.string.error_server);
            }
        });
    }

}
