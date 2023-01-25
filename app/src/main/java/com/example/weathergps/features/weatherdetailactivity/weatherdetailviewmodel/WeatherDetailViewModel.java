package com.example.weathergps.features.weatherdetailactivity.weatherdetailviewmodel;

import android.content.Context;

import com.example.weathergps.data.localstorage.DataBaseHelper;
import com.example.weathergps.data.localstorage.QueryModel;

public class WeatherDetailViewModel implements IWeatherDetailViewModel{

    DataBaseHelper dataBaseHelper;
    private String QUERY_TABLE = "QUERY_TABLE";


    @Override
    public void saveQuery(Context context, QueryModel query) {
        dataBaseHelper = new DataBaseHelper(context,QUERY_TABLE,null,1);
        dataBaseHelper.saveQuery(query);
    }
}
