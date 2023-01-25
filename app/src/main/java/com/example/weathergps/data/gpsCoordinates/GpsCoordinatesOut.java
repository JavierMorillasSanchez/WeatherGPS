package com.example.weathergps.data.gpsCoordinates;

import java.io.Serializable;

public class GpsCoordinatesOut implements Serializable {

    String validDate;
    String latitude;
    String longitude;
    Double skinTempMax;
    Double skinTempMin;
    Double skinTempAvg;

    public GpsCoordinatesOut(String validDate, String latitude, String longitude, Double skinTempMax, Double skinTempMin, Double skinTempAvg) {
        this.validDate = validDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.skinTempMax = skinTempMax;
        this.skinTempMin = skinTempMin;
        this.skinTempAvg = skinTempAvg;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
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

    public Double getSkinTempMax() {
        return skinTempMax;
    }

    public void setSkinTempMax(Double skinTempMax) {
        this.skinTempMax = skinTempMax;
    }

    public Double getSkinTempMin() {
        return skinTempMin;
    }

    public void setSkinTempMin(Double skinTempMin) {
        this.skinTempMin = skinTempMin;
    }

    public Double getSkinTempAvg() {
        return skinTempAvg;
    }

    public void setSkinTempAvg(Double skinTempAvg) {
        this.skinTempAvg = skinTempAvg;
    }
}
