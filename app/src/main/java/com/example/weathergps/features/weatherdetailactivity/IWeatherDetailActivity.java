package com.example.weathergps.features.weatherdetailactivity;

import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesOut;

public interface IWeatherDetailActivity {
    void setUIref();
    void callToWeatherApi();
    void setWeatherData(GpsCoordinatesOut gpsCoordinatesOut);

}
