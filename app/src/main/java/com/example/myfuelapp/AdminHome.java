package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    Button viewFuelStations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        viewFuelStations = (Button) findViewById(R.id.btn_pump_stations);

        viewFuelStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to view full stations
                Intent intent = new Intent(AdminHome.this,FuelStationFilterScreen.class);
                startActivity(intent);
            }
        });


    }
}