package com.svalero.airadmin.model.airplanesModels;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CREATED;

import android.content.Context;
import android.util.Log;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneRegisterContract;
import com.svalero.airadmin.domain.Airplane;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirplaneRegisterModel implements AirplaneRegisterContract.Model {
    private Context context;

    public AirplaneRegisterModel(Context context) {
        this.context = context;

    }

    public void registerAirplane(OnRegisterAirplaneListener listener, Airplane airplane) {
        AirplaneInterface api = AirplaneApi.buildInstance();
        Call<Airplane> addAirplaneCall = api.addAirplane(airplane);
        addAirplaneCall.enqueue(new Callback<Airplane>() {
            @Override
            public void onResponse(Call<Airplane> call, Response<Airplane> response) {
                if (response.code() == HTTP_CREATED) {
                    Log.e("addAirplane", response.message());
                    listener.onRegisterAirplaneSuccess();

                } else if (response.code() == HTTP_BAD_REQUEST) {
                    Log.e("addAirplane", response.message());
                    listener.onRegisterAirplaneError(R.string.error_validation);
                }

            }

            @Override
            public void onFailure(Call<Airplane> call, Throwable t) {

                Log.e("addAirplane", "Error en la solicitud: " + t.getMessage());
                listener.onRegisterAirplaneError(R.string.error_server);
            }
        });
    }

}
