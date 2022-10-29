package com.example.myfuelapp;

import androidx.appcompat.app.AlertDialog;
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
    int selectedFuelType;

    //AlertDialog.Builder loca


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
        pCot95Lit.setText(fuelStationModel.getFuelTypes().get(0).getNoOfLitters() + " L ");
        pOct92Lit.setText(fuelStationModel.getFuelTypes().get(1).getNoOfLitters() + " L ");
        dieslLit.setText(fuelStationModel.getFuelTypes().get(2).getNoOfLitters() + " L ");


    }

    public void onClickEnterToTheQue(View view){
        switch (view.getId()){
            case R.id.p95Btn:
                selectedFuelType = 0;
                break;
            case  R.id.p92Btn:
                selectedFuelType = 1;
                break;
            case R.id.dieslBtn:
                selectedFuelType = 2;
                break;
        }
        Intent intent = new Intent(FuelDetailView.this , UsersQueueSettingsActivity.class);
        intent.putExtra("FUEL_TYPE" , selectedFuelType);
        intent.putExtra("CURRENT_FUEL_STATION" , fuelStationModel);
        startActivity(intent);

    }

}