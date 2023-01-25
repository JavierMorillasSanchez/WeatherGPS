package com.example.weathergps.features.weatherdetailactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weathergps.R;
import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesOut;
import com.example.weathergps.data.localstorage.QueryModel;
import com.example.weathergps.data.weatherapicall.WeatherApiCall;
import com.example.weathergps.features.weatherdetailactivity.weatherdetailviewmodel.WeatherDetailViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WeatherDetailActivity extends AppCompatActivity implements IWeatherDetailActivity, OnMapReadyCallback {

    private WeatherDetailViewModel viewModel;
    private QueryModel query;
    private WeatherApiCall weatherApiCall;
    private TextView txtMaxTemp,txtMinTemp,txtAvgTemp, txtLatitude, txtLongitude;
    Button btnNewQuery, btnSaveQuery, btnAllQueries;
    private GpsCoordinatesIn gpsCoordinatesIn;

    private GoogleMap weatherMap;
    private SupportMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        setUIref();
        callToWeatherApi();

    }

    @Override
    public void setUIref(){
        this.viewModel = new WeatherDetailViewModel();
        this.viewModel = new WeatherDetailViewModel();
        this.txtMaxTemp = findViewById(R.id.txt_max_temp_detail);
        this.txtMinTemp = findViewById(R.id.txt_min_temp_detail);
        this.txtAvgTemp = findViewById(R.id.txt_avg_temp_detail);
        this.txtLatitude = findViewById(R.id.txt_latitude_detail);
        this.txtLongitude = findViewById(R.id.txt_longitude_detail);

        btnNewQuery = findViewById(R.id.btn_new_query_detail);
        btnAllQueries = findViewById(R.id.btn_check_queries);
        btnSaveQuery = findViewById(R.id.btn_save_query_detail);

        this.btnNewQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        this.btnSaveQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                query = new QueryModel(
                        -1,
                        gpsCoordinatesIn.getLatitude(),
                        gpsCoordinatesIn.getLongitude(),
                        String.valueOf(txtAvgTemp.getText())
                );

                viewModel.saveQuery(getApplicationContext(), query);
            }
        });

        btnAllQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.navigateToAllQueries(getApplicationContext());
            }
        });

        this.gpsCoordinatesIn = (GpsCoordinatesIn) getIntent().getSerializableExtra("gpsCoordinates");

        this.mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void callToWeatherApi() {
        this.weatherApiCall = new WeatherApiCall(gpsCoordinatesIn);
        this.weatherApiCall.execute();
        this.setWeatherData(this.weatherApiCall.gpsCoordinatesOut);
    }

    @Override
    public void setWeatherData(GpsCoordinatesOut gpsCoordinatesOut) {

        this.txtLatitude.setText(this.gpsCoordinatesIn.getLatitude());
        this.txtLongitude.setText(this.gpsCoordinatesIn.getLongitude());

        if(gpsCoordinatesOut != null){
            this.txtMaxTemp.setText(String.valueOf(gpsCoordinatesOut.getSkinTempMax()));
            this.txtMinTemp.setText(String.valueOf(gpsCoordinatesOut.getSkinTempMin()));
            this.txtAvgTemp.setText(String.valueOf(gpsCoordinatesOut.getSkinTempAvg()));
        }

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        this.weatherMap = googleMap;

        LatLng pointer = new LatLng(Double.parseDouble(this.gpsCoordinatesIn.getLatitude()),
                Double.parseDouble(this.gpsCoordinatesIn.getLatitude()));

        weatherMap.addMarker(new MarkerOptions()
                .position(pointer)
                .title(String.valueOf(R.string.you_are_here)));
        weatherMap.moveCamera(CameraUpdateFactory.newLatLng(pointer));

    }
}