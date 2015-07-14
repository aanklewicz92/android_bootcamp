package com.droidonroids.weatherbootcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.droidonroids.weatherbootcamp.adapters.ListAdapter;
import com.droidonroids.weatherbootcamp.adapters.WeatherRecyclerViewAdapter;
import com.droidonroids.weatherbootcamp.data.network.entities.City;
import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import com.droidonroids.weatherbootcamp.services.ForecastService;
import com.droidonroids.weatherbootcamp.utils.Constatns;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "BOOTCAMP";

	private ForecastService mForecastService;
	private Callback<ForecastResponse> mCallback;

	private Button mDownloadButton;
	private EditText mEditTextCity;
	private TextView mTextViewWeatherInfo;
	private ListView mListViewWeatherResponses;
	private RecyclerView mRecyclerViewWeatherResponses;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		prepareAdapter();

		findViews();
		setOnClickListeners();
	}

	private void findViews() {
		mDownloadButton = (Button) findViewById(R.id.buttonDownload);
		mEditTextCity = (EditText) findViewById(R.id.editTextCity);
		mTextViewWeatherInfo = (TextView) findViewById(R.id.weatherInfo);
		mListViewWeatherResponses = (ListView) findViewById(R.id.listViewWeatherResponses);
		mRecyclerViewWeatherResponses = (RecyclerView) findViewById(R.id.recyclerViewWeatherResponses);
	}

	private void setOnClickListeners() {
		mDownloadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getData();
			}
		});
	}

	private void prepareAdapter() {
		RequestInterceptor requestInterceptor = new RequestInterceptor() {
			@Override
			public void intercept(RequestFacade request) {
				request.addQueryParam("units", "metric");
			}
		};

		RestAdapter restAdapter = new RestAdapter.Builder()
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new AndroidLog(TAG))
				.setEndpoint(Constatns.ENDPOINT)
				.setRequestInterceptor(requestInterceptor)
				.build();

		mCallback = new Callback<ForecastResponse>() {
			@Override
			public void success(ForecastResponse forecastResponse, Response response) {
				Log.d(TAG, forecastResponse.toString());
				showWeatherInfo(forecastResponse);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.d(TAG, error.toString());
			}
		};

		mForecastService = restAdapter.create(ForecastService.class);
	}

	private void getData() {
		if(mEditTextCity.getText().toString().length() > 0)
			mForecastService.getForecastWithCallback(mEditTextCity.getText().toString(), mCallback);
		else
			Toast.makeText(this, R.string.no_city_name, Toast.LENGTH_SHORT).show();
	}

	private void showWeatherInfo(ForecastResponse forecastResponse) {
		if(forecastResponse.getWeatherResponses() != null && forecastResponse.getCity() != null) {
			City city = forecastResponse.getCity();
			List<WeatherResponse> weatherResponses = forecastResponse.getWeatherResponses();
			String data = city.toString() + "\n\n";
			for(WeatherResponse weatherResponse : weatherResponses)
				data += weatherResponse.toString() + "\n\n";
			mTextViewWeatherInfo.setText(data);

			ListAdapter listViewAdapter = new ListAdapter(this, weatherResponses);
			mListViewWeatherResponses.setAdapter(listViewAdapter);

			WeatherRecyclerViewAdapter recyclerViewAdapter = new WeatherRecyclerViewAdapter(this, weatherResponses);

			LinearLayoutManager layoutManager = new LinearLayoutManager(this);
			mRecyclerViewWeatherResponses.setLayoutManager(layoutManager);
			mRecyclerViewWeatherResponses.setAdapter(recyclerViewAdapter);
		} else {
			mTextViewWeatherInfo.setText(R.string.no_data);
		}
	}
}
