package com.example.myfuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.linkLogin:
                startActivity(new Intent(this, MainActivity.class));

        }
    }
}