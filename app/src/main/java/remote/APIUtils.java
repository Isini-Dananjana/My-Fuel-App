package remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://localhost:5191/api/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

    public static FuelStationService getFuelStationService(){
        return RetrofitClient.getClient(API_URL).create(FuelStationService.class);
    }
}
