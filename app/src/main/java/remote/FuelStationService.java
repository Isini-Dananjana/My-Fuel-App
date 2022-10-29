package remote;

import java.util.List;

import model.FuelStationModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FuelStationService {

    String ENDPOINT_NAME = "fuelstation/";

    @GET(ENDPOINT_NAME)
    Call<List<FuelStationModel>> getFuelStations();

    @GET(ENDPOINT_NAME + "{id}")
    Call<List<FuelStationModel>> getFuelStationsById();

    @POST(ENDPOINT_NAME)
    Call<FuelStationModel> addFuelStation(@Body FuelStationModel fuelStation);


    @PUT(ENDPOINT_NAME+"{id}")
    Call<FuelStationModel> updateFuelStation(@Path("id") String id, @Body FuelStationModel fuelStation);

    @DELETE(ENDPOINT_NAME+"{id}")
    Call<FuelStationModel> deleteFuelStation(@Path("id") int id);
}
