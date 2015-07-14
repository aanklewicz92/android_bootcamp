package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "name=" + name +
                '}';
    }
}
