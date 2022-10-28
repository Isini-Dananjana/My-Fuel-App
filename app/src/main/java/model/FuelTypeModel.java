package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FuelTypeModel implements Serializable {

    @SerializedName("fuelType")
    @Expose
    private String fuelType ;
    @SerializedName("available")
    @Expose
    private Boolean available ;

    public int getQueSize() {
        return queSize;
    }

    public void setQueSize(int queSize) {
        this.queSize = queSize;
    }

    @SerializedName("noOfLitters")
    @Expose
    private double noOfLitters ;

    @SerializedName("queSize")
    private int queSize;

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
