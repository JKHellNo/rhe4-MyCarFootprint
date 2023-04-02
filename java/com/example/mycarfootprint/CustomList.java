package com.example.mycarfootprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<Station> {
    private ArrayList<Station> stations;
    private Context context;

    public CustomList(Context context, ArrayList<Station> stations) {
        super(context, 0, stations);
        this.stations = stations;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {view = LayoutInflater.from(context).inflate(R.layout.context, parent, false);}
        Station city = stations.get(position);
        TextView stationName = view.findViewById(R.id.station_text);
        TextView carbonCount = view.findViewById(R.id.carbonCount_text);
        TextView fuelName = view.findViewById(R.id.fuel_text);
        stationName.setText(city.getStationName());
        carbonCount.setText(city.getCarbon());
        fuelName.setText(city.getFuelName());
        return view;
    }
}
