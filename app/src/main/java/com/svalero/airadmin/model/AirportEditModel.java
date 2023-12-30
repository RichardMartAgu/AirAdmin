package com.svalero.airadmin.model;

import static java.net.HttpURLConnection.HTTP_OK;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.svalero.airadmin.api.AirApi;
import com.svalero.airadmin.api.AirportInterface;
import com.svalero.airadmin.contract.AirportEditContract;
import com.svalero.airadmin.domain.Airport;
import com.svalero.airadmin.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportEditModel implements AirportEditContract.Model {
    private Context context;

    public AirportEditModel(Context context) {
        this.context = context;
    }

    public void loadEditOneAirport(long airportId,Airport airport, AirportEditContract.Model.OnLoadEditOneAirportListener listener) {
        AirportInterface api = AirApi.buildInstance();
        Call<Airport> editAirportCall = api.editAirportById(airportId, airport);
        editAirportCall.enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.code() == HTTP_OK) {
                    Log.e("editAirport", response.message());
                    listener.onLoadEditOneAirportSuccess();
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
                    Toast.makeText(context, "Error de validación en los datos de entrada", Toast.LENGTH_SHORT).show();

                } else {
                    String errorMessage = ErrorUtils.parseError(response, context);
                    Log.e("editAirport", "Error en la respuesta: " + errorMessage);
                    listener.onLoadEditOneAirportError("Error en la respuesta del servidor: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                if (t.getMessage() != null && t.getMessage().contains("End of input at line 1 column 1 path $")) {
                    return;
                } else {

                    Log.e("editAirport", "Error en la solicitud: " + t.getMessage());
                    listener.onLoadEditOneAirportError("Se ha producido un error al conectar con el servidor");
                }
            }
        });
    }
}


