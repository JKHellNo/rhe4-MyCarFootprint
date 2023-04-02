package com.example.mycarfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddStationFragment.OnFragmentInteractionListener {
    ListView stationList;
    ArrayAdapter<Station> stationAdapter;
    ArrayList<Station> stationDataList;

    private AdapterView.OnItemClickListener cityClickedHandler = (parent, v, position, id) -> {
        Station station = stationDataList.get(position);
        AddStationFragment stationFragment = new AddStationFragment(station);
        stationFragment.setTitle("Edit city");
        stationFragment.show(getSupportFragmentManager(), "ADD_CITY");
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stationList = findViewById(R.id.city_list);
        stationDataList = new ArrayList<Station>();
        stationAdapter = new CustomList(this, stationDataList);
        stationList.setAdapter(stationAdapter);
        stationList.setOnItemClickListener(cityClickedHandler);

        final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);   //add a gas station
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStationFragment addCityFragment = new AddStationFragment();
                addCityFragment.setTitle("Add city");
                addCityFragment.show(getSupportFragmentManager(), "ADD_CITY");
            }
        });
    }

    public void TotalBalanceChange() {   //determine total cost and total carbon footprint
        double totalGasCost=0.00;
        int totalEmissionRelease=0;
        TextView totalCost = findViewById(R.id.total_cost);
        TextView totalEmission = findViewById(R.id.total_emission);

        for (int i = 0; i < stationDataList.size(); i++){             //goes through each station in arraylist
            double tempGasCost= stationDataList.get(i).getTotalCost();
            int tempEmission=Integer.parseInt(stationDataList.get(i).getCarbon());
            totalGasCost+=tempGasCost;
            totalEmissionRelease+=tempEmission;
        }
        totalCost.setText("Total Expense:"+ String.valueOf(totalGasCost));
        totalEmission.setText("Total Emission:" + String.valueOf(totalEmissionRelease));
    }

    @Override
    public void AddStation(Station station) {
        stationAdapter.add(station);
    }

    @Override
    public void EditStation(Station station, String newStationName, String newPrice, String newLitre, String newFuelName, String newDate) {
        station.setStationName(newStationName);
        station.setPrice((newPrice));
        station.setLitres(newLitre);
        station.setFuelName(newFuelName);
        station.setDate(newDate);
        stationAdapter.notifyDataSetChanged();
    }
    public void DeleteStation(Station station) {stationAdapter.remove(station);}

}