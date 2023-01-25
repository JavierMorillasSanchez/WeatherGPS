package com.example.weathergps.data.localstorage;

public class QueryModel {

    private int id;
    private String latitude;
    private String longitude;
    private String avg_temp;

    public QueryModel(int id, String latitude, String longitude, String avg_temp) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avg_temp = avg_temp;
    }

    @Override
    public String toString() {
        return "QueryModel{" +
                "id='" + id + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", avg_temp='" + avg_temp + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAvg_temp() {
        return avg_temp;
    }

    public void setAvg_temp(String avg_temp) {
        this.avg_temp = avg_temp;
    }
}
