package com.example.myfuelapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.FuelStationModel;

public class FuelStationAdapterView   extends ArrayAdapter<FuelStationModel> {

    private Activity context;
    List<FuelStationModel> fuelStations;
    FuelStationModel currFuelStation;


    public FuelStationAdapterView(Activity context, List<FuelStationModel> fuelStationModelList){
        super(context,R.layout.single_shed_item,fuelStationModelList);
        this.context = context;
        this.fuelStations =  fuelStationModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        currFuelStation = fuelStations.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.single_shed_item, null, true);
        TextView shedName = (TextView) listViewItem.findViewById(R.id.singleItemShedName);
        shedName.setText(currFuelStation.getName());
        return listViewItem;
    }
}
