package com.svalero.airadmin.api;

import com.svalero.airadmin.domain.Airplane;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AirplaneInterface {

    @GET("airplanes")
    Call<List<Airplane>> getAirplanes();
    @GET("airplane/{airplaneId}")
    Call<Airplane> getAirplaneById(@Path("airplaneId") long airplaneId);

    @POST("airplane")
    Call<Airplane> addAirplane(@Body Airplane airplane);

    @DELETE("airplane/{airplaneId}")
    Call<Airplane> deleteAirplaneById(@Path("airplaneId")long airplaneId );

    @PUT("airplane/{airplaneId}")
    Call<Airplane> editAirplaneById(@Path("airplaneId")long airplaneId,@Body Airplane airplane);

}

