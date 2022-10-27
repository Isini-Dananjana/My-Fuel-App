package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.FuelStationModel;
import model.User;
import remote.APIUtils;
import remote.FuelStationService;
import remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelStationFilterScreen extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    ListView listView;

    FuelStationService  fuelStationService;
    List<FuelStationModel> fuelStationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fuelStationService = APIUtils.getFuelStationService();
        setContentView(R.layout.activity_fuel_station_filter_screen);

        listView = (ListView) findViewById(R.id.shedListView);
        autoCompleteTextView = findViewById(R.id.AutoCompleteTextview);
        String[] Subjects = new String[]{"Kadawatha", "Gampaha", "Colombo" , "Kandy" , "Trinco"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_single_item, Subjects);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "" + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show());

        fuelStationList = new ArrayList<>();

        FuelStationModel fuelStationModel = new FuelStationModel();
        fuelStationModel.setName("Kadawatha IOC 1");
        FuelStationModel fuelStationModel1 = new FuelStationModel();
        fuelStationModel1.setName("Ragama 1");

        fuelStationList.add(fuelStationModel);
        fuelStationList.add(fuelStationModel1);

        FuelStationAdapterView fuelStationAdapterView = new FuelStationAdapterView(FuelStationFilterScreen.this , fuelStationList);
        listView.setAdapter(fuelStationAdapterView);


        //getFuelStations();

    }



    public void getFuelStations(){
        Call<List<FuelStationModel>> call = fuelStationService.getFuelStations();
        call.enqueue(new Callback<List<FuelStationModel>>() {
            @Override
            public void onResponse(Call<List<FuelStationModel>> call, Response<List<FuelStationModel>> response) {
                if(response.isSuccessful()){
                    fuelStationList = response.body();
                }
            }
            @Override
            public void onFailure(Call<List<FuelStationModel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void OnclickFuelStation(int id){
        FuelStationModel fuelStationModel = fuelStationList.get(id);
        Intent intent =  new Intent(FuelStationFilterScreen.this , FuelDetailView.class);
        intent.putExtra("CURR_FUEl_STATION",fuelStationModel);
    }
}