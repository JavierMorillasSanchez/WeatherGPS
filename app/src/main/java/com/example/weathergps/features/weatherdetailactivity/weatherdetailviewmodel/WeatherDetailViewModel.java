package com.example.weathergps.features.weatherdetailactivity.weatherdetailviewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.weathergps.R;
import com.example.weathergps.data.localstorage.DataBaseHelper;
import com.example.weathergps.data.localstorage.QueryModel;
import com.example.weathergps.features.mainactivity.MainActivity;
import com.example.weathergps.features.querylistactivity.QueryListActivity;

import java.util.ArrayList;

public class WeatherDetailViewModel implements IWeatherDetailViewModel{

    DataBaseHelper dataBaseHelper;
    private String QUERY_TABLE = "QUERY_TABLE";


    @Override
    public void saveQuery(Context context, QueryModel query) {
        dataBaseHelper = new DataBaseHelper(context,QUERY_TABLE,null,1);
        dataBaseHelper.saveQuery(query);
        Toast.makeText(context, R.string.saved_query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToAllQueries(Context context) {
        Intent intent = new Intent(context, QueryListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void navigateToNewQuery(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
