package com.example.weathergps.features.mainactivity.mainactivityviewmodel;

import android.content.Context;
import android.content.Intent;

import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.data.localstorage.QueryModel;
import com.example.weathergps.features.querylistactivity.QueryListActivity;
import com.example.weathergps.features.weatherdetailactivity.WeatherDetailActivity;

public class MainActivityViewModel implements IMainActivityViewModel{
    @Override
    public void navigateToWeatherDetail(Context context, GpsCoordinatesIn gpsCoordinatesIn) {
        Intent intent = new Intent(context, WeatherDetailActivity.class);
        intent.putExtra("gpsCoordinates", gpsCoordinatesIn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void navigateToAllQueries(Context context){
        Intent navigateToUserDetail = new Intent(context, QueryListActivity.class);
        navigateToUserDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(navigateToUserDetail);
    }

}
