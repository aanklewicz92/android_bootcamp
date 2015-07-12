package com.droidonroids.weatherbootcamp.data.network;

import com.droidonroids.weatherbootcamp.data.network.api.WeatherService;
import com.droidonroids.weatherbootcamp.data.network.api.WeatherRequestInterceptor;
import com.droidonroids.weatherbootcamp.data.network.consts.Const;
import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import rx.Observable;

public class ApiManager implements ApiManagerImpl {
	private static final String TAG = ApiManager.class.getSimpleName();
	private WeatherService mWeatherService;

	public ApiManager() {
		RestAdapter restAdapter = this.initRestAdapter();
		mWeatherService = restAdapter.create(WeatherService.class);
	}

	private RestAdapter initRestAdapter() {
		return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
			.setLog(new AndroidLog(TAG))
			.setRequestInterceptor(new WeatherRequestInterceptor().getRequestInterceptor())
			.setEndpoint(Const.ENDPOINT)
			.build();
	}

	@Override public Observable<WeatherResponse> getWeatherWithObservable(String cityName) {
		return mWeatherService.getWeatherWithObservable(cityName);
	}

	@Override
	public void getWeatherWithCallback(String cityName, Callback<WeatherResponse> callback) {
		mWeatherService.getWeatherWithCallback(cityName, callback);
	}

	@Override public WeatherResponse getWeatherWithSync(String cityName) {
		return mWeatherService.getWeatherWithSync(cityName);
	}

	@Override public Observable<ForecastResponse> getForecastWithObservable(String cityName) {
		return mWeatherService.getForecastWithObservable(cityName);
	}
}