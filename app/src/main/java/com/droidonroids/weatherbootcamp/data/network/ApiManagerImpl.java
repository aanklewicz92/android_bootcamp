package com.droidonroids.weatherbootcamp.data.network;

import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import retrofit.Callback;
import rx.Observable;

public interface ApiManagerImpl {
	Observable<WeatherResponse> getWeatherWithObservable(String cityName);

	void getWeatherWithCallback(String cityName, Callback<WeatherResponse> callback);

	WeatherResponse getWeatherWithAsync(String cityName);

	Observable<ForecastResponse> getForecastWithObservable(String cityName);
}
