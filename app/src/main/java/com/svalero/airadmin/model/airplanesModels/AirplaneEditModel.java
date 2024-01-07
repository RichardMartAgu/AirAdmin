package com.svalero.airadmin.model.airplanesModels;

import static java.net.HttpURLConnection.HTTP_OK;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneEditContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirplaneEditModel implements AirplaneEditContract.Model {
    private Context context;

    public AirplaneEditModel(Context context) {
        this.context = context;
    }

    public void loadEditOneAirplane(long airplaneId,Airplane airplane, OnLoadEditOneAirplaneListener listener) {
        AirplaneInterface api = AirplaneApi.buildInstance();
        Call<Airplane> editAirplaneCall = api.editAirplaneById(airplaneId, airplane);
        editAirplaneCall.enqueue(new Callback<Airplane>() {
            @Override
            public void onResponse(Call<Airplane> call, Response<Airplane> response) {
                if (response.code() == HTTP_OK) {
                    Log.e("editAirplane", response.message());
                    listener.onLoadEditOneAirplaneSuccess();
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
                    Toast.makeText(context, "Error de validación en los datos de entrada", Toast.LENGTH_SHORT).show();

                } else {
                    String errorMessage = ErrorUtils.parseError(response, context);
                    Log.e("editAirplane", "Error en la respuesta: " + errorMessage);
                    listener.onLoadEditOneAirplaneError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airplane> call, Throwable t) {
                if (t.getMessage() != null && t.getMessage().contains("End of input at line 1 column 1 path $")) {
                    return;
                } else {

                    Log.e("editAirplane", "Error en la solicitud: " + t.getMessage());
                    listener.onLoadEditOneAirplaneError("Se ha producido un error al conectar con el servidor");
                }
            }
        });
    }
}


