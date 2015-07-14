package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponse {
    @SerializedName("list")
    private List<WeatherResponse> weatherResponses;
    @SerializedName("city")
    private City city;

    public List<WeatherResponse> getWeatherResponses() {
        return this.weatherResponses;
    }

    public void setWeatherResponses(List<WeatherResponse> weatherResponses) {
        this.weatherResponses = weatherResponses;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ForecastResponse{" +
                "city=" + city +
                "weatherResponses=" + weatherResponses +
                '}';
    }
}
