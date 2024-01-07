package com.svalero.airadmin.model.airplanesModels;

import static java.net.HttpURLConnection.HTTP_OK;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.svalero.airadmin.api.AirplaneApi;
import com.svalero.airadmin.api.AirplaneInterface;
import com.svalero.airadmin.contract.airplanesContracts.AirplaneRegisterContract;
import com.svalero.airadmin.domain.Airplane;
import com.svalero.airadmin.utils.ErrorUtils;

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
                if (response.code() == HTTP_OK) {
                    Log.e("addAirplane", response.message());
                    listener.onRegisterAirplaneSuccess();
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    
                } else if (response.code() == 400) {
                    Toast.makeText(context, "Error de validación en los datos de entrada", Toast.LENGTH_SHORT).show();
                    
                } else {
                    String errorMessage = ErrorUtils.parseError(response, context);
                    Log.e("addAirplane", "Error en la respuesta: " + errorMessage);
                    listener.onRegisterAirplaneError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airplane> call, Throwable t) {
                if (t.getMessage() != null && t.getMessage().contains("End of input at line 1 column 1 path $")) {
                    return;
                } else {

                    Log.e("addAirplane", "Error en la solicitud: " + t.getMessage());
                    listener.onRegisterAirplaneError("Se ha producido un error al conectar con el servidor");
                }
            }
        });
    }

}
