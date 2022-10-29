package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import model.FuelStationModel;
import model.FuelTypeModel;
import model.User;
import remote.APIUtils;
import remote.FuelStationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersQueueSettingsActivity extends AppCompatActivity {

    int fuelType = 0;
    FuelStationModel fuelStationModel;
    TextView fuelStationName;
    TextView  queSize;
    FuelStationService fuelStationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_queue_settings);
        Intent intent =  getIntent();
        fuelStationModel = (FuelStationModel) intent.getSerializableExtra("CURRENT_FUEL_STATION");
        fuelType = (int)intent.getIntExtra("FUEL_TYPE" , 0);
        fuelStationName = findViewById(R.id.queSettingFuelStationName);
        queSize =  findViewById(R.id.queSizeLable);
        fuelStationService = APIUtils.getFuelStationService();

        //Set Values
        fuelStationName.setText(fuelStationModel.getName());

        System.out.println(fuelType);
        //update que size

        int prevSize = fuelStationModel.getFuelTypes().get(fuelType).getQueSize();
        fuelStationModel.getFuelTypes().get(fuelType).setQueSize(prevSize + 1);

        queSize.setText( Integer.toString(fuelStationModel.getFuelTypes().get(fuelType).getQueSize()));

        //update data base
        //updateFuelStation(fuelStationModel);

    }

    public void updateFuelStation(FuelStationModel fuelStationModel){
        Call<FuelStationModel> call = fuelStationService.updateFuelStation(fuelStationModel.getId() , fuelStationModel);
        call.enqueue(new Callback<FuelStationModel>() {
            @Override
            public void onResponse(Call<FuelStationModel> call, Response<FuelStationModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UsersQueueSettingsActivity.this, "You have entered to the queue!", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(UsersQueueSettingsActivity.this, "Queue update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelStationModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}