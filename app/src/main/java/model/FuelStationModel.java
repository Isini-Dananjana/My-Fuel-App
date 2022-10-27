package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FuelStationModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("currentQueSize")
    @Expose
    private int currentQueSize;
    @SerializedName("averageTimeSpent")
    @Expose
    private double averageTimeSpent;
    @SerializedName("updateVersionCount")
    @Expose
    private int updateVersionCount;

    @SerializedName("fuelTypes")
    @Expose
    private List<FuelTypeModel> fuelTypes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentQueSize() {
        return currentQueSize;
    }

    public void setCurrentQueSize(int currentQueSize) {
        this.currentQueSize = currentQueSize;
    }

    public double getAverageTimeSpent() {
        return averageTimeSpent;
    }

    public void setAverageTimeSpent(double averageTimeSpent) {
        this.averageTimeSpent = averageTimeSpent;
    }

    public int getUpdateVersionCount() {
        return updateVersionCount;
    }

    public void setUpdateVersionCount(int updateVersionCount) {
        this.updateVersionCount = updateVersionCount;
    }

    public List<FuelTypeModel> getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(List<FuelTypeModel> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }
}
