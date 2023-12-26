package com.svalero.airadmin.api;


import com.svalero.airadmin.domain.Airport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AirApiInterface {

    @GET("airports")
    Call<List<Airport>> getAirports();

    @POST("airports")
    Call<Airport> addAirport(@Body Airport airport);

    @DELETE("airport/{airportId}")
    Call<Void> deleteAirport(@Path("airportId") long airportId);
}

