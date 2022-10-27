package remote;


import java.util.List;

import model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    String ENDPOINT_NAME = "user/";

    @GET(ENDPOINT_NAME)
    Call<List<User>> getUsers();

    @POST(ENDPOINT_NAME)
    Call<User> addUser(@Body User user);

    @POST("user/login/")
    Call<User> login(@Body User user);

    @PUT(ENDPOINT_NAME+"{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE(ENDPOINT_NAME+"{id}")
    Call<User> deleteUser(@Path("id") int id);
}
