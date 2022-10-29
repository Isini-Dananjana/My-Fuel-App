package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.DecimalFormat;

public class UsersQueueSettingsActivity extends AppCompatActivity {

    int fuelType = 0;
    FuelStationModel fuelStationModel;
    TextView fuelStationName;
    TextView  queSize;
    FuelStationService fuelStationService;
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startTime = System.currentTimeMillis();
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
        updateFuelStation(fuelStationModel);

    }

    public void onClickLeave(View view){

        long currentTime = System.currentTimeMillis();
        long timDif = currentTime - startTime;
        double timeDifInMin = ((timDif / 1000.00 ) / (60.0));

        DecimalFormat df = new DecimalFormat("0.00");
        String timeFormated = df.format(timeDifInMin);
        //Ask are you sure want to leave
        new MaterialAlertDialogBuilder(UsersQueueSettingsActivity.this).setMessage("You have already spent  ? "  + timeFormated + " minutes .").setTitle("Are you wan to leve ?")
                // Add customization options here
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //update average spent time
                        int updateVersionCount = fuelStationModel.getUpdateVersionCount();
                        double currentAverageSpentTime = fuelStationModel.getAverageTimeSpent();
                        double currentActualSpentTime = currentAverageSpentTime * updateVersionCount;
                        currentActualSpentTime = currentActualSpentTime + timeDifInMin;
                         double newAverageSpentTime = currentActualSpentTime / (updateVersionCount + 1);
                         fuelStationModel.setAverageTimeSpent(newAverageSpentTime);
                         fuelStationModel.setUpdateVersionCount(updateVersionCount+1);
                        int prevSize = fuelStationModel.getFuelTypes().get(fuelType).getQueSize();
                        fuelStationModel.getFuelTypes().get(fuelType).setQueSize(prevSize - 1);

                        queSize.setText( Integer.toString(fuelStationModel.getFuelTypes().get(fuelType).getQueSize()));
                        dialogInterface.cancel();
                        updateFuelStation(fuelStationModel);
                        Intent intent = new Intent(UsersQueueSettingsActivity.this , FuelStationFilterScreen.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        })
                .show();



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