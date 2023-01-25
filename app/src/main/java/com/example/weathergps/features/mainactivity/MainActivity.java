package com.example.weathergps.features.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weathergps.R;
import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.features.mainactivity.mainactivityviewmodel.MainActivityViewModel;
import com.example.weathergps.utils.apiutils.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private EditText txtLatitude, txtLongitude;
    private Button btnCheckWeater;
    private TextView txtLastSearches;
    private GpsCoordinatesIn gpsCoordinatesIn;
    private String apiKey, startDate, endDate, latitude, longitude;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUIref();
    }


    @Override
    public void setUIref(){

        txtLatitude = findViewById(R.id.etxt_latitude);
        txtLongitude = findViewById(R.id.etxt_longitude);

        btnCheckWeater = findViewById(R.id.btn_check_weather);
        txtLastSearches = findViewById(R.id.txt_last_searches);
        this.viewModel = new MainActivityViewModel();

        btnCheckWeater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToWeatherDetail();
            }
        });

    }

    @Override
    public GpsCoordinatesIn gpsRequest() {
        this.apiKey = new ApiUtils().getApiKey();
        this.latitude =  String.valueOf(txtLatitude.getText());
        this.longitude = String.valueOf(txtLongitude.getText());
        this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());;

        gpsCoordinatesIn = new GpsCoordinatesIn(
                apiKey,
                latitude,
                longitude,
                startDate,
                endDate
        );

        return gpsCoordinatesIn;
    }

    @Override
    public void navigateToWeatherDetail() {
        if(!txtLatitude.getText().toString().equals("") && !txtLongitude.getText().toString().equals("")){
            this.viewModel.navigateToWeatherDetail(getApplicationContext(),gpsRequest());
        } else {
            Toast.makeText(getApplicationContext(), R.string.toast_message_no_dates, Toast.LENGTH_SHORT).show();
        }
    }


}