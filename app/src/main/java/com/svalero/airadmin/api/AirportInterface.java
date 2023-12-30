package com.svalero.airadmin.api;


import com.svalero.airadmin.domain.Airport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AirportInterface {

    @GET("airports")
    Call<List<Airport>> getAirports();
    @GET("airport/{airportId}")
    Call<Airport> getAirportById(@Path("airportId") long airportId);

    @POST("airports")
    Call<Airport> addAirport(@Body Airport airport);

    @DELETE("airport/{airportId}")
    Call<Airport> deleteAirportById(@Path("airportId")long airportId );

    @PUT("airport/{airportId}")
    Call<Airport> editAirportById(@Path("airportId")long airportId,@Body Airport airport);


}

