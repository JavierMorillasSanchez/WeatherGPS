package com.example.weathergps.features.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathergps.R;
import com.example.weathergps.component.recyclerview.QueriesAdapter;
import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.data.localstorage.DataBaseHelper;
import com.example.weathergps.data.localstorage.QueryModel;
import com.example.weathergps.features.mainactivity.mainactivityviewmodel.MainActivityViewModel;
import com.example.weathergps.features.querylistactivity.QueryListActivity;
import com.example.weathergps.utils.apiutils.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private EditText txtLatitude, txtLongitude;
    private Button btnCheckWeater, btnAllQueries;
    private TextView txtLastSearches;
    private GpsCoordinatesIn gpsCoordinatesIn;
    private String apiKey, startDate, endDate, latitude, longitude;
    private MainActivityViewModel viewModel;

    DataBaseHelper dataBaseHelper;
    private String QUERY_TABLE = "QUERY_TABLE";

    private RecyclerView rvLastQueries;
    public ArrayList<QueryModel> everyQuery, lastQueries;
    public QueriesAdapter queriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUIref();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLastQueries();
    }

    @Override
    public void setUIref(){

        rvLastQueries = (RecyclerView) findViewById(R.id.rv_last_queries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rvLastQueries.setLayoutManager(linearLayoutManager);

        txtLatitude = findViewById(R.id.etxt_latitude);
        txtLongitude = findViewById(R.id.etxt_longitude);
        txtLastSearches = findViewById(R.id.txt_last_searches);

        btnCheckWeater = findViewById(R.id.btn_check_weather);
        btnAllQueries = findViewById(R.id.btn_all_queries);

        this.viewModel = new MainActivityViewModel();

        btnCheckWeater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtLatitude.getText().toString().equals("") && !txtLongitude.getText().toString().equals("")){
                    viewModel.navigateToWeatherDetail(getApplicationContext(),gpsRequest());
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_message_no_dates, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAllQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.navigateToAllQueries(getApplicationContext());
            }
        });
    }

    @Override
    public void setLastQueries() {

        this.dataBaseHelper = new DataBaseHelper(getApplicationContext(),QUERY_TABLE,null,1);
        this.everyQuery = dataBaseHelper.getAllQueries();

        lastQueries = new ArrayList<>();

        if(everyQuery != null && everyQuery.size() > 0){
            lastQueries.add(everyQuery.get(everyQuery.size()-1));
            if(everyQuery.size() > 1){
                lastQueries.add(everyQuery.get(everyQuery.size()-2));
                if(everyQuery.size() > 2){
                    lastQueries.add(everyQuery.get(everyQuery.size()-3));
                }
            }
        }


        this.queriesAdapter = new QueriesAdapter(getApplicationContext(), this.lastQueries);
        rvLastQueries.setAdapter(this.queriesAdapter);

    }

    @Override
    public GpsCoordinatesIn gpsRequest() {
        this.apiKey = new ApiUtils().getApiKey();
        this.latitude =  String.valueOf(txtLatitude.getText());
        this.longitude = String.valueOf(txtLongitude.getText());
        this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        gpsCoordinatesIn = new GpsCoordinatesIn(
                apiKey,
                latitude,
                longitude,
                startDate,
                endDate
        );

        return gpsCoordinatesIn;
    }

}