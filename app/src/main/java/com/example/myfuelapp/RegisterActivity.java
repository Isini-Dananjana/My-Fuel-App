package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
    boolean valid = true;


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

                validateData();
                if(valid){
                    User user = new User();
                    user.setFirstName(firstName.getText().toString());
                    user.setLastName(lastName.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setMobile(mobile.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setType("customer");
                    addUser(user);
                }

            }
        });
    }

    public void validateData(){
        valid = true;
        if(firstName.getText().toString().isEmpty()){
            //Toast.makeText(RegisterActivity.this, "Please enter the first name", Toast.LENGTH_LONG).show();
            firstName.setError("This field is required ");
            valid = false;
        }
        if(lastName.getText().toString().isEmpty()){
            //Toast.makeText(RegisterActivity.this, "Please enter the first name", Toast.LENGTH_LONG).show();
            lastName.setError("This field is required ");
            valid = false;
        }
        if(email.getText().toString().isEmpty()){
            //Toast.makeText(RegisterActivity.this, "Please enter the first name", Toast.LENGTH_LONG).show();
            email.setError("This field is required ");
            valid = false;
        }

        if(mobile.getText().toString().isEmpty()){
            //Toast.makeText(RegisterActivity.this, "Please enter the first name", Toast.LENGTH_LONG).show();
            mobile.setError("This field is required ");
            valid = false;
        }

        if(password.getText().toString().isEmpty()){
            //Toast.makeText(RegisterActivity.this, "Please enter the first name", Toast.LENGTH_LONG).show();
            password.setError("This field is required ");
            valid = false;
        }

        if(confirmPassword.getText().toString().isEmpty()){
            //Toast.makeText(RegisterActivity.this, "Please enter the first name", Toast.LENGTH_LONG).show();
            confirmPassword.setError("This field is required ");
            valid = false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Please enter a valid email address ");
            valid = false;
        }

        if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            confirmPassword.setError("Password mismatch !");
            valid = false;
        }

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


    public void onClick(View view) {
        switch(view.getId()){
            case R.id.linkLogin:
                startActivity(new Intent(this, MainActivity.class));

        }
    }
}