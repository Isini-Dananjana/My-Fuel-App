package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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



    }
}