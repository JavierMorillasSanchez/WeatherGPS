package com.example.weathergps.features.weatherdetailactivity.weatherdetailviewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.weathergps.data.localstorage.DataBaseHelper;
import com.example.weathergps.data.localstorage.QueryModel;
import com.example.weathergps.features.querylistactivity.QueryListActivity;

import java.util.ArrayList;

public class WeatherDetailViewModel implements IWeatherDetailViewModel{

    DataBaseHelper dataBaseHelper;
    private String QUERY_TABLE = "QUERY_TABLE";


    @Override
    public void saveQuery(Context context, QueryModel query) {
        dataBaseHelper = new DataBaseHelper(context,QUERY_TABLE,null,1);
        dataBaseHelper.saveQuery(query);
    }

    @Override
    public void navigateToAllQueries(Context context) {
        Intent intent = new Intent(context, QueryListActivity.class);
        context.startActivity(intent);
    }

    /*
    dataBaseHelper = new DataBaseHelper(context,QUERY_TABLE,null,1);
        ArrayList<QueryModel> everyone = dataBaseHelper.getAllQueries();
     */

}
