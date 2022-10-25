package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class FuelStationFilterScreen extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_filter_screen);

        autoCompleteTextView = findViewById(R.id.AutoCompleteTextview);
        String[] Subjects = new String[]{"Kadawatha", "Gampaha", "Colombo"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_single_item, Subjects);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "" + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show());



    }
}