package com.droidonroids.weatherbootcamp.data.network;

import com.droidonroids.weatherbootcamp.data.network.api.RestAdapterApi;
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
	private RestAdapterApi mRestAdapterApi;

	public ApiManager() {
		RestAdapter restAdapter = this.initRestAdapter();
		mRestAdapterApi = restAdapter.create(RestAdapterApi.class);
	}

	private RestAdapter initRestAdapter() {
		return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
			.setLog(new AndroidLog(TAG))
			.setRequestInterceptor(new WeatherRequestInterceptor().getRequestInterceptor())
			.setEndpoint(Const.ENDPOINT)
			.build();
	}

	@Override public Observable<WeatherResponse> getWeatherWithObservable(String cityName) {
		return mRestAdapterApi.getWeatherWithObservable(cityName);
	}

	@Override
	public void getWeatherWithCallback(String cityName, Callback<WeatherResponse> callback) {
		mRestAdapterApi.getWeatherWithCallback(cityName, callback);
	}

	@Override public WeatherResponse getWeatherWithAsync(String cityName) {
		return mRestAdapterApi.getWeatherWithAsync(cityName);
	}

	@Override public Observable<ForecastResponse> getForecastWithObservable(String cityName) {
		return mRestAdapterApi.getForecastWithObservable(cityName);
	}
}