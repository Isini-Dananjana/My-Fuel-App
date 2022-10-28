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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    //filtered list
    List<FuelStationModel> filteredFuelList;
    HashSet<String> areas = new HashSet<String>(){{
        add("All");
    }};

    String selectedArea = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fuelStationService = APIUtils.getFuelStationService();
        setContentView(R.layout.activity_fuel_station_filter_screen);

        listView = (ListView) findViewById(R.id.shedListView);
        autoCompleteTextView = findViewById(R.id.AutoCompleteTextview);
        //autoCompleteTextView.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "" + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show());

        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            TextView selectedView = (TextView) view;
            selectedArea = selectedView.getText().toString();
            getFuelStations();
        });
        getFuelStations();


    }



    public void getFuelStations(){
        Call<List<FuelStationModel>> call = fuelStationService.getFuelStations();
        call.enqueue(new Callback<List<FuelStationModel>>() {
            @Override
            public void onResponse(Call<List<FuelStationModel>> call, Response<List<FuelStationModel>> response) {
                if(response.isSuccessful()){
                   fuelStationList = response.body();
                    if(fuelStationList != null || !fuelStationList.isEmpty()){
                        //Get available areas


                        for(FuelStationModel fuelStationModel : fuelStationList){

                            areas.add(fuelStationModel.getArea());
                        }

                        String areasArray[] = new String[areas.size()];
                        areas.toArray(areasArray);

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(FuelStationFilterScreen.this, R.layout.drop_down_single_item, areasArray);
                        autoCompleteTextView.setAdapter(adapter);
                        if(selectedArea.equals("All")){
                            setAdapterView(fuelStationList);
                        }else{
                            //Should filter and set Adapter
                            filteredFuelList = new ArrayList<>();
                            for(FuelStationModel fuelStationModel : fuelStationList){

                                if(fuelStationModel.getArea().equals(selectedArea)){
                                    filteredFuelList.add(fuelStationModel);

                                }
                            }
                            setAdapterView(filteredFuelList);
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


    public void setAdapterView(List<FuelStationModel> list){
        FuelStationAdapterView fuelStationAdapterView = new FuelStationAdapterView(FuelStationFilterScreen.this , list);
        listView.setAdapter(fuelStationAdapterView);
    }

    public void OnclickFuelStation(int id){
        FuelStationModel fuelStationModel = fuelStationList.get(id);
        Intent intent =  new Intent(FuelStationFilterScreen.this , FuelDetailView.class);
        intent.putExtra("CURR_FUEl_STATION",fuelStationModel);
    }
}