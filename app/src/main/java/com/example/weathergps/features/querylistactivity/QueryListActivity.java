package com.example.weathergps.features.querylistactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weathergps.R;

public class QueryListActivity extends AppCompatActivity implements IQueryListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_list);
        setUIRef();
    }

    @Override
    public void setUIRef() {

    }
}