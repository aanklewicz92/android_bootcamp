package com.droidonroids.weatherbootcamp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import com.droidonroids.weatherbootcamp.utils.ImageURLBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter {
    public static final int ITEM_TYPE_BIG_WEATHER = 0;

    private List<WeatherResponse> mWeatherResponses;
    private Context mContext;

    public WeatherRecyclerViewAdapter(Context context, List<WeatherResponse> weatherResponses) {
        mWeatherResponses = weatherResponses;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item_weather, parent, false);
        return new RecyclerWeatherBigItemHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        WeatherResponse weatherResponse = mWeatherResponses.get(position);

        switch (itemType) {
            case ITEM_TYPE_BIG_WEATHER:
                setBigItemData(holder, weatherResponse);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mWeatherResponses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE_BIG_WEATHER;
    }

    private void setBigItemData(RecyclerView.ViewHolder holder, WeatherResponse weatherResponse) {
        RecyclerWeatherBigItemHolder bigItemHolder = (RecyclerWeatherBigItemHolder) holder;
        bigItemHolder.mTextViewTempMin.setText(weatherResponse.getMain().getTempMin() + "");
        bigItemHolder.mTextViewTempMax.setText(weatherResponse.getMain().getTempMax() + "");
        bigItemHolder.mTextViewPressure.setText(weatherResponse.getMain().getPressure() + "");
        bigItemHolder.mTextViewTemperature.setText(weatherResponse.getMain().getTemp() + "");

        Picasso.with(mContext)
                .load(ImageURLBuilder.getImageURL(weatherResponse.getWeathers().get(0).getIcon()))
                .resize(60, 60)
                .into(bigItemHolder.mImageViewImage);
    }

    public static class RecyclerWeatherBigItemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView)
        public ImageView mImageViewImage;
        @Bind(R.id.textViewTempMin)
        public TextView mTextViewTempMin;
        @Bind(R.id.textViewTempMax)
        public TextView mTextViewTempMax;
        @Bind(R.id.textViewPressure)
        public TextView mTextViewPressure;
        @Bind(R.id.textViewTemperature)
        public TextView mTextViewTemperature;

        public RecyclerWeatherBigItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
