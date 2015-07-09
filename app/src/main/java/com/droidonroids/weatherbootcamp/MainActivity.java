package com.droidonroids.weatherbootcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.droidonroids.weatherbootcamp.data.network.ApiManager;

public class MainActivity extends AppCompatActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d("ApiManager", "onCreate pre");
		ApiManager apiManager = new ApiManager();
		apiManager.getWeatherWithObservable("Wrocław").subscribe();
		Log.d("ApiManager", "onCreate");
	}
}
