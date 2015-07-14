package com.droidonroids.weatherbootcamp.services;

import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ForecastService {
    @GET("/forecast")
    void getForecastWithCallback(@Query("q") String cityName, Callback<ForecastResponse> callback);
}
