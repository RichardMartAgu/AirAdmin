package com.svalero.airadmin.model;

import static java.net.HttpURLConnection.HTTP_OK;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.svalero.airadmin.api.AirApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.AirportRegisterContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportRegisterModel implements AirportRegisterContract.Model {
    private Context context;

    public AirportRegisterModel(Context context) {
        this.context = context;
    }

    public void registerAirport(OnRegisterAirportListener listener, Airport airport) {
        AirportInterface api = AirApi.buildInstance();
        Call<Airport> addAirportCall = api.addAirport(airport);
        addAirportCall.enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.code() == HTTP_OK) {
                    Log.e("addAirport", response.message());
                    listener.onRegisterAirportSuccess();
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    
                } else if (response.code() == 400) {
                    Toast.makeText(context, "Error de validación en los datos de entrada", Toast.LENGTH_SHORT).show();
                    
                } else {
                    String errorMessage = ErrorUtils.parseError(response, context);
                    Log.e("addAirport", "Error en la respuesta: " + errorMessage);
                    listener.onRegisterAirportError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                if (t.getMessage() != null && t.getMessage().contains("End of input at line 1 column 1 path $")) {
                    return;
                } else {

                    Log.e("addAirport", "Error en la solicitud: " + t.getMessage());
                    listener.onRegisterAirportError("Se ha producido un error al conectar con el servidor");
                }
            }
        });
    }

}
