package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelTypeModel {

    @SerializedName("fuelType")
    @Expose
    private String fuelType ;
    @SerializedName("available")
    @Expose
    private Boolean available ;
    @SerializedName("noOfLitters")
    @Expose
    private double noOfLitters ;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public double getNoOfLitters() {
        return noOfLitters;
    }

    public void setNoOfLitters(double noOfLitters) {
        this.noOfLitters = noOfLitters;
    }
}
