package com.example.weathergps.component.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathergps.BuildConfig;
import com.example.weathergps.R;
import com.example.weathergps.component.listitemcell.QueryViewHolder;
import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesIn;
import com.example.weathergps.data.localstorage.QueryModel;
import com.example.weathergps.features.weatherdetailactivity.WeatherDetailActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QueriesAdapter extends RecyclerView.Adapter<QueryViewHolder> implements IQueriesAdapter {

    private Context context;
    private ArrayList<QueryModel> queryModel = new ArrayList<>();
    GpsCoordinatesIn coordinates;

    public QueriesAdapter(Context context, ArrayList<QueryModel> queryModel){
        this.context = context;
        this.queryModel = queryModel;
    }

    @NonNull
    @Override
    public QueryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_item_cell, parent, false);
        return new QueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryViewHolder holder, @SuppressLint("RecyclerView") int position) {

        QueryModel query = queryModel.get(position);

        holder.txtLatitude.setText(query.getLatitude());
        holder.txtLongitude.setText(query.getLongitude());
        holder.txtAvgTemp.setText(query.getAvg_temp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coordinates = new GpsCoordinatesIn(
                        BuildConfig.WEATHER_API_KEY,
                        query.getLatitude(),
                        query.getLongitude(),
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                );

                navigateToWeatherDetail(coordinates);
            }
        });

    }

    @Override
    public int getItemCount() {
        return queryModel.size();
    }

    @Override
    public void navigateToWeatherDetail(GpsCoordinatesIn coordinates){
        Intent navigateToUserDetail = new Intent(context, WeatherDetailActivity.class);
        navigateToUserDetail.putExtra("gpsCoordinates",coordinates);
        navigateToUserDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(navigateToUserDetail);
    }

}
