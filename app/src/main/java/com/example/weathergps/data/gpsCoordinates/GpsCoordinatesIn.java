package com.example.weathergps.data.gpsCoordinates;

import java.io.Serializable;

public class GpsCoordinatesIn implements Serializable {

    private String APIkey;
    private String latitude;
    private String longitude;
    private String startDate;
    private String endDate;

    public GpsCoordinatesIn(String APIkey, String latitude, String longitude, String startDate, String endDate) {
        this.APIkey = APIkey;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getAPIkey() {
        return APIkey;
    }

    public void setAPIkey(String APIkey) {
        this.APIkey = APIkey;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
