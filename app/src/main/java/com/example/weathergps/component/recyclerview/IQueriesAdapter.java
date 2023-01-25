package com.example.weathergps.component.recyclerview;

import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.data.localstorage.QueryModel;

public interface IQueriesAdapter {
    void navigateToWeatherDetail(GpsCoordinatesIn coordinates);
}
