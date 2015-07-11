package com.droidonroids.weatherbootcamp.data.network.api;

import retrofit.RequestInterceptor;

public class WeatherRequestInterceptor {
	public RequestInterceptor getRequestInterceptor() {
		return new RequestInterceptor() {
			@Override public void intercept(RequestFacade request) {
				request.addQueryParam("units", "metric");
			}
		};
	}
}