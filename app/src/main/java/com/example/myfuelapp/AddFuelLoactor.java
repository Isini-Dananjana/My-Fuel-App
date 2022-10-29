package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

import model.FuelStationModel;
import model.FuelTypeModel;

public class AddFuelLoactor extends AppCompatActivity {
    FuelStationModel fuelStation;
    EditText stationName, stationLocation, petrol92, petrol95, diesel;
    double p92;
    double p95;
    double d;
    List<FuelTypeModel> fuelTypeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel_loactor);

        Intent intent = getIntent();
        fuelStation = (FuelStationModel) intent.getSerializableExtra("FUEL_STATION_DETAILS");

        stationName = findViewById(R.id.etFuelStationName);
        stationLocation = findViewById(R.id.etFuelStationLocation2);
        petrol92 = findViewById(R.id.editTextPetrol92);
        petrol95 = findViewById(R.id.editTextPetrol95);
        diesel = findViewById(R.id.editTextDiesel);

        stationName.setText(fuelStation.getName());
        stationLocation.setText(fuelStation.getArea());

        fuelTypeList = fuelStation.getFuelTypes();
        //Get fuel types
        for (FuelTypeModel fuelType : fuelTypeList) {
            if (fuelType.getFuelType().equals("P95")) {
                p95 = fuelType.getNoOfLitters();
            } else if (fuelType.getFuelType().equals("P92")) {
                p92 = fuelType.getNoOfLitters();
            } else {
                d = fuelType.getNoOfLitters();
            }


            petrol92.setText(Double.toString(p92));
            petrol95.setText(Double.toString(p95));
            diesel.setText(Double.toString(d));

        }
    }
}