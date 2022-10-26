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
        setContentView(R.layout.activity_add_fuel_loactor);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signup:
                startActivity(new Intent(this, RegisterActivity.class));

        }
    }

    private void readInputValues(){



    }
}