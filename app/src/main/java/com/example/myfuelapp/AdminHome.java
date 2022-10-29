package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import model.FuelStationModel;
import remote.APIUtils;
import remote.FuelStationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminHome extends AppCompatActivity {

    FuelStationService fuelStationService;
    List<FuelStationModel> fuelStationList;
    FuelStationModel filteredFuelStation;
    String userId = "635c3c5108345fe78e7e6e4d";

    Button viewFuelStations,updateFuelLocator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        viewFuelStations = (Button) findViewById(R.id.btn_pump_stations);
        updateFuelLocator = (Button) findViewById(R.id.btn_update_fuel_stat);

        fuelStationService = APIUtils.getFuelStationService();

//        viewFuelStations.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Navigate to view full stations
//                Intent intent = new Intent(AdminHome.this,FuelStationFilterScreen.class);
//                startActivity(intent);
//            }
//        });

        getFuelStations();
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_update_fuel_stat:
                Intent intent =  new Intent(this,AddFuelLoactor.class);
                intent.putExtra("FUEL_STATION_DETAILS", filteredFuelStation);
                startActivity(intent);
                break;
            case R.id.btn_pump_stations:
                startActivity(new Intent(this,FuelStationFilterScreen.class));
                break;

        }
    }

    public void getFuelStations(){
        Call<List<FuelStationModel>> call = fuelStationService.getFuelStations();
        call.enqueue(new Callback<List<FuelStationModel>>() {
            @Override
            public void onResponse(Call<List<FuelStationModel>> call, Response<List<FuelStationModel>> response) {
                if(response.isSuccessful()){
                    fuelStationList = response.body();
                    if(fuelStationList != null || !fuelStationList.isEmpty()){

                        //Get owner's station
                        for(FuelStationModel fuelStationModel : fuelStationList){
                            if(fuelStationModel.getOwnerId().equals(userId)){
                                filteredFuelStation= fuelStationModel;
                            }
                        }

                    }
                }
            }
            @Override
            public void onFailure(Call<List<FuelStationModel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}