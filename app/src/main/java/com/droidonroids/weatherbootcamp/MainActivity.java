package com.droidonroids.weatherbootcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.droidonroids.weatherbootcamp.data.network.ApiManager;
import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "BOOTCAMP";

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ApiManager apiManager = new ApiManager();
		apiManager.getForecastWithObservable("Wrocław")
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Action1<ForecastResponse>() {
				@Override public void call(ForecastResponse forecastResponse) {
					Log.d(TAG, forecastResponse.toString());
				}
			});
	}
}
