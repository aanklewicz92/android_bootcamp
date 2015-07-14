package com.droidonroids.weatherbootcamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import com.droidonroids.weatherbootcamp.utils.ImageURLBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListAdapter extends ArrayAdapter<WeatherResponse> {
    private LayoutInflater mInflater;
    private Context mContext;

    public ListAdapter(Context context, List<WeatherResponse> weatherResponseList) {
        super(context, R.layout.lv_item_weather, weatherResponseList);
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.lv_item_weather, parent, false);

            holder = new WeatherViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (WeatherViewHolder) convertView.getTag();

        WeatherResponse weatherResponse = getItem(position);

        holder.mTextViewTempMin.setText(weatherResponse.getMain().getTempMin() + "");
        holder.mTextViewTempMax.setText(weatherResponse.getMain().getTempMax() + "");
        holder.mTextViewPressure.setText(weatherResponse.getMain().getPressure() + "");
        holder.mTextViewTemperature.setText(weatherResponse.getMain().getTemp() + "");

        Picasso.with(mContext)
                .load(ImageURLBuilder.getImageURL(weatherResponse.getWeathers().get(0).getIcon()))
                .resize(60, 60)
                .into(holder.mImageViewImage);

        return convertView;
    }

    public static class WeatherViewHolder {
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

        WeatherViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
