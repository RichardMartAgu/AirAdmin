package com.svalero.airadmin.model.airportsModel;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;

import android.content.Context;
import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirportApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.airportsContracts.AirportEditContract;
import com.svalero.airadmin.domain.Airport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportEditModel implements AirportEditContract.Model {
    private Context context;

    public AirportEditModel(Context context) {
        this.context = context;
    }

    public void loadEditOneAirport(long airportId, Airport airport, AirportEditContract.Model.OnLoadEditOneAirportListener listener) {
        AirportInterface api = AirportApi.buildInstance();
        Call<Airport> editAirportCall = api.editAirportById(airportId, airport);
        editAirportCall.enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.code() == HTTP_NO_CONTENT) {
                    Log.e("editAirport", response.message());
                    listener.onLoadEditOneAirportSuccess();
                } else if (response.code() == HTTP_BAD_REQUEST) {
                    listener.onLoadEditOneAirportError(R.string.error_validation);
                } else if (response.code() == HTTP_NOT_FOUND) {
                    listener.onLoadEditOneAirportError(R.string.error_airport_not_found);

                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                if (t.getMessage() != null && t.getMessage().contains("End of input at line 1 column 1 path $")) {
                    return;
                } else {

                    Log.e("editAirport", "Error en la solicitud: " + t.getMessage());
                    listener.onLoadEditOneAirportError(R.string.error_server);
                }
            }
        });
    }
}


