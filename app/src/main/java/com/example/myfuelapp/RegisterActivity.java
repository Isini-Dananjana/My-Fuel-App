package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import model.User;
import remote.APIUtils;
import remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    UserService userService;
    List<User> list = new ArrayList<User>();
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText mobile;
    EditText password;
    EditText confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = (Button) findViewById(R.id.btnRegisterFuel);
        userService = APIUtils.getUserService();
        readInputValues();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                User user = new User();
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setEmail(email.getText().toString());
                user.setMobile(mobile.getText().toString());
                user.setPassword(password.getText().toString());
                user.setType("customer");


                addUser(user);
            }
        });
    }

    public void addUser(User u){
        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(myIntent);

                }else{
                    Toast.makeText(RegisterActivity.this, "Email address is already in use", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void readInputValues(){

        firstName  = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        email = (EditText) findViewById(R.id.etEmail);
        mobile = (EditText) findViewById(R.id.editTextPhone);
        password = (EditText) findViewById(R.id.etPassword);
        confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

    }

    public void getUsersList(){
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list = response.body();

                    for(User s : list){
                        System.out.println(s.getFirstName());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.linkLogin:
                startActivity(new Intent(this, MainActivity.class));

        }
    }
}