package com.example.weathergps.features.mainactivity.mainactivityviewmodel;

import android.content.Context;
import android.content.Intent;

import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.features.weatherdetailactivity.WeatherDetailActivity;

public class MainActivityViewModel implements IMainActivityViewModel{
    @Override
    public void navigateToWeatherDetail(Context context, GpsCoordinatesIn gpsCoordinatesIn) {
        Intent intent = new Intent(context, WeatherDetailActivity.class);
        intent.putExtra("gpsCoordinates", gpsCoordinatesIn);
        context.startActivity(intent);
    }
}
