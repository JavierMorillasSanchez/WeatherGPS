package com.example.weathergps.features.mainactivity.mainactivityviewmodel;

import android.content.Context;

import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;

public interface IMainActivityViewModel {
    void navigateToWeatherDetail(Context context, GpsCoordinatesIn gpsCoordinatesIn);
}
