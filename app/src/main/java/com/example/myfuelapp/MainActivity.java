package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import model.User;
import remote.APIUtils;
import remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readInputValues();
        userService = APIUtils.getUserService();

        /*
        * Get user entered password and email
        * */
        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(email.getText().toString());

        /*
        * Send api request to check login
        * */

        User loggedUser = userLogin(user);


        if(loggedUser.getId() != null){

            Toast.makeText(this,"User Logged in successfully ",Toast.LENGTH_LONG).show();

            switch (loggedUser.getType()){
                case "customer":
                    //Navigate to customer  UI
                    Intent intent = new Intent(this,FuelStationFilterScreen.class);
                    startActivity(intent);
                default:
                    //Navigate to admin UI
                    Intent intent1 = new Intent(this,AdminHome.class);
                    startActivity(intent1);
            }
        }



    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signup:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this, FuelStationFilterScreen.class));
                break;
        }
    }

    private void readInputValues(){

        email =  (EditText) findViewById(R.id.etLoginEmail);
        password = (EditText) findViewById(R.id.etLoginPassword);

    }

    public User userLogin(User u){
        Call<User> call = userService.addUser(u);
        User userResponse = new User();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                   userResponse.setId(response.body().getId());
                   userResponse.setEmail(response.body().getEmail());
                   userResponse.setType(response.body().getType());
                   userResponse.setMobile(response.body().getMobile());
                   userResponse.setFirstName(response.body().getFirstName());
                   userResponse.setLastName(response.body().getLastName());

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
        return  userResponse;
    }



}