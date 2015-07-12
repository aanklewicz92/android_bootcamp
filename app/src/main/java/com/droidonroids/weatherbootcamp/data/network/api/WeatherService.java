package com.droidonroids.weatherbootcamp.data.network.api;

import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherService {

	@GET("/weather") void getWeatherWithCallback(@Query("q") String cityName,
		Callback<WeatherResponse> callback);

	@GET("/weather") Observable<WeatherResponse> getWeatherWithObservable(
		@Query("q") String cityName);

	@GET("/weather") WeatherResponse getWeatherWithSync(@Query("q") String cityName);

	@GET("/forecast")Observable<ForecastResponse> getForecastWithObservable(@Query("q") String cityName);
}