package com.example.mycarfootprint;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class AddStationFragment extends DialogFragment {
    private String title = "Add Station";
    private Station station;
    private Boolean isEdit = false;
    private EditText stationName;
    private EditText price;
    private EditText litre;
    private Button diesel;
    private Button gasoline;
    private String fuel;
    private TextView date;
    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener {
        void AddStation(Station Station);

        void EditStation(Station station, String newStationName, String newPrice, String newLitre, String FuelName, String newDate);

        void DeleteStation(Station station);

        void TotalBalanceChange();
    }
    public AddStationFragment(Station station) {
        this.station = station;
        this.isEdit = true;
    }
    public AddStationFragment() {
        super();
    };

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_station_fragment_layout, null);
        stationName = view.findViewById(R.id.gas_station);
        price = view.findViewById(R.id.price_per_litre);
        litre = view.findViewById(R.id.litres_bought);
        diesel = view.findViewById(R.id.Diesel);
        gasoline = view.findViewById(R.id.Gasoline);
        date = view.findViewById(R.id.DatePicker);

        diesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuel="Diesel";
            }
        });

        gasoline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuel="Gasoline";
            }
        });

        Calendar cal=Calendar.getInstance();  //get current day for temporary usage
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String selectDate = dayOfMonth+ "/"+ month+ "/"+year;
                date.setText(selectDate);
            }
        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),style, dateListener, year, month, day);
                datePickerDialog.show();
            }
        });


        if (isEdit) {   // If editing, add the previous data entries
            stationName.setText(station.getStationName());
            price.setText(station.getPrice());
            litre.setText(station.getLitres());
            fuel = station.getFuelName();
            date.setText(station.getDate());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", null)
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.DeleteStation(station);
                        listener.TotalBalanceChange();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String stationInput = stationName.getText().toString();
                        String litreInput = litre.getText().toString();
                        String priceInput = price.getText().toString();
                        String dateInput = date.getText().toString();
                        if (isEdit) {
                            listener.EditStation(station, stationInput, priceInput, litreInput, fuel, dateInput);
                        } else {
                            listener.AddStation(new Station(stationInput, priceInput, litreInput, fuel, dateInput));
                        }
                        listener.TotalBalanceChange();
                    }
                }).create();
    }
}
