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



    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signup:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btnLogin:
                handleLogin();
                break;
        }
    }

    private void readInputValues(){

        email =  (EditText) findViewById(R.id.etLoginEmail);
        password = (EditText) findViewById(R.id.etLoginPassword);

    }


    public void handleLogin(){
        /*
         * Get user entered password and email
         * */
        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        /*
         * Send api request to check login
         * */

        User loggedUser = userLogin(user);



    }
    public User userLogin(User u){
        u.setId("");
        u.setFirstName("");
        u.setLastName("");
        u.setMobile("");
        u.setType("");

        Call<User> call = userService.login(u);
        User userResponse = new User();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){

                    if(response.body().getId() != null){

                        Toast.makeText(MainActivity.this,"User Logged in successfully ",Toast.LENGTH_LONG).show();

                        switch (response.body().getType()){
                            case "customer":
                                //Navigate to customer  UI
                                Intent intent = new Intent(MainActivity.this,FuelStationFilterScreen.class);
                                startActivity(intent);
                                break;
                            default:
                                //Navigate to admin UI
                                Intent intent1 = new Intent(MainActivity.this,AdminHome.class);
                                startActivity(intent1);
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"User Login failed ",Toast.LENGTH_LONG).show();
                    }

                }else{

                    Toast.makeText(MainActivity.this,"User Login failed invalid password or email",Toast.LENGTH_LONG).show();
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