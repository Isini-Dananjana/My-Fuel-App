package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import model.FuelStationModel;
import model.FuelTypeModel;
import remote.APIUtils;
import remote.FuelStationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFuelLoactor extends AppCompatActivity {
    FuelStationModel fuelStation;
    EditText stationName, stationLocation, petrol92, petrol95, diesel;
    FuelTypeModel p92, p95, d;
    List<FuelTypeModel> fuelTypeList;
    FuelStationService fuelStationService;
    Button btnUpdate;
    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel_loactor);

        btnUpdate = (Button) findViewById(R.id.btnUpdateFuelStationAdmin);
        valid = false;
        Intent intent = getIntent();
        fuelStation = (FuelStationModel) intent.getSerializableExtra("FUEL_STATION_DETAILS");
        fuelStationService = APIUtils.getFuelStationService();
        stationName = findViewById(R.id.etFuelStationName);
        stationLocation = findViewById(R.id.etFuelStationLocation2);
        petrol92 = findViewById(R.id.editTextPetrol92);
        petrol95 = findViewById(R.id.editTextPetrol95);
        diesel = findViewById(R.id.editTextDiesel);

        stationName.setText(fuelStation.getName());
        stationLocation.setText(fuelStation.getArea());

        stationName.setFocusable(false);
        stationLocation.setFocusable(false);

        fuelTypeList = fuelStation.getFuelTypes();
        //Get fuel types
        for (FuelTypeModel fuelType : fuelTypeList) {
            if (fuelType.getFuelType().equals("P95")) {
                p95 = fuelType;
            }
            else if (fuelType.getFuelType().equals("P92")) {
                p92 = fuelType;
            }
            else {
                d = fuelType;
            }
        }
        petrol92.setText(Double.toString(p92.getNoOfLitters()));
        petrol95.setText(Double.toString(p95.getNoOfLitters()));
        diesel.setText(Double.toString(d.getNoOfLitters()));
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnUpdateFuelStationAdmin:
                if (valid){
                    p92.setNoOfLitters(Double.parseDouble(petrol92.getText().toString()));
                    p95.setNoOfLitters(Double.parseDouble(petrol95.getText().toString()));
                    d.setNoOfLitters(Double.parseDouble(diesel.getText().toString()));
                    fuelStation.setFuelTypes(fuelTypeList);
                    updateFuelStation(fuelStation);
                }
                break;
        }
    }

    public void validate(){
        if(Double.parseDouble(petrol92.getText().toString())<0 || Double.parseDouble(petrol95.getText().toString())<0 || Double.parseDouble(diesel.getText().toString())<0  ){
            valid = false;
            Toast.makeText(AddFuelLoactor.this, "Number of liters cannot be a negative value", Toast.LENGTH_SHORT).show();
        }
        else if(petrol92.getText().toString().isEmpty() || petrol95.getText().toString().isEmpty() || diesel.getText().toString().isEmpty()){
            valid = false;
            Toast.makeText(AddFuelLoactor.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else
            valid = true;

    }

    public void updateFuelStation(FuelStationModel fuelStationModel){
        Call<FuelStationModel> call = fuelStationService.updateFuelStation(fuelStationModel.getId() , fuelStationModel);
        call.enqueue(new Callback<FuelStationModel>() {
            @Override
            public void onResponse(Call<FuelStationModel> call, Response<FuelStationModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddFuelLoactor.this, "Successfully updated details!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(AddFuelLoactor.this, "update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FuelStationModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}