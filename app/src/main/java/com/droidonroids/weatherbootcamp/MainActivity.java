package com.droidonroids.weatherbootcamp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.droidonroids.weatherbootcamp.data.network.ApiManager;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "BOOTCAMP";

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ApiManager apiManager = new ApiManager();
		apiManager.getWeatherWithObservable("Wrocław")
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Action1<WeatherResponse>() {
				@Override public void call(WeatherResponse weatherResponse) {
					Log.d(TAG, "\nOBSERVABLE\n");
					Log.d(TAG, weatherResponse.toString());
				}
			});

		Callback<WeatherResponse> callback = new Callback<WeatherResponse>() {
			@Override public void success(WeatherResponse weatherResponse, Response response) {
				Log.d(TAG, "\nCALLBACK\n");
				Log.d(TAG, weatherResponse.toString());
			}

			@Override public void failure(RetrofitError error) {
				Log.d(TAG, error.toString());
			}
		};
		apiManager.getWeatherWithCallback("Wrocław", callback);

		new AsyncTask<String, Void, WeatherResponse>() {
			@Override protected WeatherResponse doInBackground(String... params) {
				return apiManager.getWeatherWithSync(params[0]);
			}

			@Override protected void onPostExecute(WeatherResponse weatherResponse) {
				Log.d(TAG, "\nASYNC\n");
				Log.d(TAG, weatherResponse.toString());
			}
		}.execute("Wrocław");
	}
}
