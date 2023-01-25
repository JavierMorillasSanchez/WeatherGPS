package com.example.weathergps.utils.apiutils;

import com.example.weathergps.BuildConfig;

public class ApiUtils implements IApiUtils {
    public String apiKey = BuildConfig.WEATHER_API_KEY;

    @Override
    public String getApiKey() {
        return this.apiKey;
    }

}
