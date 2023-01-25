package com.example.weathergps.component.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathergps.R;
import com.example.weathergps.component.listitemcell.QueryViewHolder;
import com.example.weathergps.data.gpsCoordinates.GpsCoordinatesOut;
import com.example.weathergps.data.localstorage.QueryModel;
import com.example.weathergps.features.weatherdetailactivity.WeatherDetailActivity;

import java.util.ArrayList;

public class QueriesAdapter extends RecyclerView.Adapter<QueryViewHolder> implements IQueriesAdapter {

    private Context context;
    private ArrayList<QueryModel> queryModel = new ArrayList<>();

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
                navigateToWeatherDetail(query);
            }
        });

    }

    @Override
    public int getItemCount() {
        return queryModel.size();
    }

    public void updateData(ArrayList<GpsCoordinatesOut> userList) {
        if (userList.size() > 0) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void navigateToWeatherDetail(QueryModel query){
        Intent navigateToUserDetail = new Intent(context, WeatherDetailActivity.class);
        navigateToUserDetail.putExtra("query",query);
        navigateToUserDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(navigateToUserDetail);
    }

}
