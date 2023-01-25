package com.example.weathergps.features.weatherdetailactivity.weatherdetailviewmodel;

import android.content.Context;

import com.example.weathergps.data.localstorage.QueryModel;

public interface IWeatherDetailViewModel {
    void saveQuery(Context context, QueryModel query);
}
