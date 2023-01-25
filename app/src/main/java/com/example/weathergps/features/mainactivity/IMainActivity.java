package com.example.weathergps.features.mainactivity;

import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;

public interface IMainActivity {
    void setUIref();
    void setLastQueries();
    GpsCoordinatesIn gpsRequest();
}
