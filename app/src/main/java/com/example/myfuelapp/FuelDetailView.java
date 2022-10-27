package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.FuelStationModel;

public class FuelDetailView extends AppCompatActivity {


    TextView averageTime;
    TextView name;
    FuelStationModel fuelStationModel;
    TextView petrol95Liters;
    TextView petrol92Liters;
    TextView  diesl;
    Button enterToThePetrol;
    Button enterToDiesl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_detail_view);
        Intent intent =  getIntent();
        fuelStationModel = (FuelStationModel) intent.getSerializableExtra("CURR_FUEL_STATION");

        /*
        * Read display values
        * */

        averageTime = findViewById(R.id.textView11);
        name = findViewById(R.id.textView12);
        petrol92Liters = findViewById(R.id.textView14);
        petrol95Liters = findViewById(R.id.textView3);
        diesl = findViewById(R.id.textView2);
        enterToThePetrol = findViewById(R.id.enterToThePetronQueButton);
        enterToDiesl = findViewById(R.id.outlinedButtonptDiesl);


        /*
        * Set display values
        * */

        averageTime.setText((int) fuelStationModel.getAverageTimeSpent());
        name.setText(fuelStationModel.getName());
        petrol92Liters.setText((int) fuelStationModel.getFuelTypes().get(0).getNoOfLitters());
        petrol95Liters.setText((int) fuelStationModel.getFuelTypes().get(1).getNoOfLitters());
        diesl.setText((int) fuelStationModel.getFuelTypes().get(2).getNoOfLitters());

        /*
        * Enter to petrol que
        * */

        enterToThePetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(FuelDetailView.this,UsersQueueSettingsActivity.class);
                startActivity(intent1);
            }
        });

        /*
        * Enter to diesel
        *
        * **/

        enterToDiesl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(FuelDetailView.this , UsersQueueSettingsActivity.class);
                startActivity(intent2);
            }
        });

    }

}