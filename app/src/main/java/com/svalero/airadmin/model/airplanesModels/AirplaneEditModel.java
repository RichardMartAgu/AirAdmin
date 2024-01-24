package com.svalero.airadmin.model.airplanesModels;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.svalero.airadmin.R;
import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneEditContract;
import com.svalero.airadmin.domain.Airplane;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirplaneEditModel implements AirplaneEditContract.Model {
    private Context context;

    public AirplaneEditModel(Context context) {
        this.context = context;
    }

    public void loadEditOneAirplane(long airplaneId, Airplane airplane, OnLoadEditOneAirplaneListener listener) {
        AirplaneInterface api = AirplaneApi.buildInstance();
        Call<Airplane> editAirplaneCall = api.editAirplaneById(airplaneId, airplane);
        editAirplaneCall.enqueue(new Callback<Airplane>() {
            @Override
            public void onResponse(Call<Airplane> call, Response<Airplane> response) {
                if (response.code() == HTTP_NO_CONTENT) {
                    Log.e("editAirport", response.message());
                    listener.onLoadEditOneAirplaneSuccess();
                    Toast.makeText(context, "Editado con éxito", Toast.LENGTH_SHORT).show();
                } else if (response.code() == HTTP_BAD_REQUEST) {
                    listener.onLoadEditOneAirplaneError(R.string.error_validation);

                } else if (response.code() == HTTP_NOT_FOUND) {
                    listener.onLoadEditOneAirplaneError(R.string.error_airplane_not_found);
                }
            }

            @Override
            public void onFailure(Call<Airplane> call, Throwable t) {
                Log.e("editAirplane", "Error en la solicitud: " + t.getMessage());
                listener.onLoadEditOneAirplaneError(R.string.error_server);

            }
        });
    }
}


