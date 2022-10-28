package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import model.FuelStationModel;

public class FuelDetailView extends AppCompatActivity {



    FuelStationModel fuelStationModel;
    TextView averageSpentTime;
    TextView areaName;
    TextView fuelStationName;
    TextView pCot95Lit;
    TextView pOct92Lit;
    TextView dieslLit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_detail_view);
        Intent intent =  getIntent();
        fuelStationModel = (FuelStationModel) intent.getSerializableExtra("CURRENT_FUEL_STATION");
        averageSpentTime = findViewById(R.id.averageSpentTime);
        areaName =  findViewById(R.id.areaName);
        fuelStationName = findViewById(R.id.detailViewFuelStationName);

        pCot95Lit = findViewById(R.id.pCot95Litr);
        pOct92Lit = findViewById(R.id.ptOct92Liter);
        dieslLit =  findViewById(R.id.dieslLitr);



        /**
         *  Get average spent time
         * */

        int hours = (int) (fuelStationModel.getAverageTimeSpent() / 60);
        double remaningMins = (double) (fuelStationModel.getAverageTimeSpent() % 60 );
        averageSpentTime.setText("" + hours + " hours, " + remaningMins + " minutes .");

        /**
         *  Set area name
        **/

        areaName.setText(fuelStationModel.getArea() + " Sri Lanka");

        //  Fuel station name
        fuelStationName.setText(fuelStationModel.getName());
        //Set fuel values



    }

}