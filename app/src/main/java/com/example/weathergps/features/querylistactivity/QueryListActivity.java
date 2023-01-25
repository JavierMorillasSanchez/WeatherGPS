package com.example.weathergps.features.querylistactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathergps.R;
import com.example.weathergps.component.recyclerview.QueriesAdapter;
import com.example.weathergps.data.localstorage.DataBaseHelper;
import com.example.weathergps.data.localstorage.QueryModel;

import java.util.ArrayList;

public class QueryListActivity extends AppCompatActivity implements IQueryListActivity {

    DataBaseHelper dataBaseHelper;
    private String QUERY_TABLE = "QUERY_TABLE";

    private RecyclerView rvAllQueries;
    public ArrayList<QueryModel> everyQuery;
    public QueriesAdapter queriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_list);
        setUIRef();
        setListOfQueries();
    }

    @Override
    public void setUIRef() {

        this.rvAllQueries = (RecyclerView) findViewById(R.id.rv_all_queries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QueryListActivity.this, RecyclerView.VERTICAL, false);
        rvAllQueries.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setListOfQueries() {

        this.dataBaseHelper = new DataBaseHelper(getApplicationContext(),QUERY_TABLE,null,1);
        this.everyQuery = dataBaseHelper.getAllQueries();

        this.queriesAdapter = new QueriesAdapter(getApplicationContext(), this.everyQuery);
        rvAllQueries.setAdapter(this.queriesAdapter);

    }




}