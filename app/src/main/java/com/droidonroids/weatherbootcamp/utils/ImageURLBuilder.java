package com.droidonroids.weatherbootcamp.utils;

public class ImageURLBuilder {
    public static String getImageURL(String imageName) {
        return Constatns.IMAGE_URL_PREFIX + imageName + Constatns.WEATHER_IMAGE_FORMAT;
    }
}
