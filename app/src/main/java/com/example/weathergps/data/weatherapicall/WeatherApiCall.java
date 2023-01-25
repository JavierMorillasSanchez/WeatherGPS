package com.example.weathergps.data.weatherapicall;

import android.os.AsyncTask;

import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesOut;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiCall extends AsyncTask<Void,Void,String> implements IWeatherApiCall {

    String urlWeatherApi, latitude,longitude, startDate,endDate, apiKey;
    public GpsCoordinatesOut gpsCoordinatesOut;

    public WeatherApiCall(GpsCoordinatesIn gpsCoordinatesIn) {
        this.latitude = gpsCoordinatesIn.getLatitude();
        this.longitude = gpsCoordinatesIn.getLongitude();
        this.startDate = gpsCoordinatesIn.getStartDate();
        this.endDate = gpsCoordinatesIn.getEndDate();
        this.apiKey = gpsCoordinatesIn.getAPIkey();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.setUrl();
    }

    @Override
    protected String doInBackground(Void... Voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(urlWeatherApi);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }

            if (stringBuffer.length() == 0) {
                return null;
            } else {
                return stringBuffer.toString();
            }

        } catch (IOException e) {

            return null;

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String jsonStr) {
        super.onPostExecute(jsonStr);

        if (jsonStr != null) {

            try {

                JSONObject rootJsonObject = new JSONObject(jsonStr);
                JSONArray userJsonArray = rootJsonObject.getJSONArray("data");

                for (int x = 0; x < userJsonArray.length(); x++) {

                    //TODO Javi aqui
                    JSONObject jsonObject = userJsonArray.getJSONObject(x);

                    gpsCoordinatesOut = new GpsCoordinatesOut(
                            jsonObject.getString("valid_date"),
                            this.latitude,
                            this.longitude,
                            jsonObject.getDouble("skin_temp_max"),
                            jsonObject.getDouble("skin_temp_min"),
                            jsonObject.getDouble("skin_temp_avg")
                    );

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public String setUrl() {
        this.urlWeatherApi =  "https://api.weatherbit.io/v2.0/history/agweather"
                +"?lat="+ latitude
                +"&long"+longitude
                +"&start_date="+startDate
                +"&end_date="+endDate
                +"&key="+apiKey;

        return urlWeatherApi;
    }

}
