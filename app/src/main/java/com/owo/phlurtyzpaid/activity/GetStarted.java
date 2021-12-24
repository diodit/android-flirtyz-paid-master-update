package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.owo.phlurtyzpaid.R;

public class GetStarted extends AppCompatActivity {

    private Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        login = findViewById(R.id.button);
        register = findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUserLogin()){

                    Intent intent = new Intent(GetStarted.this, WelcomeScreen.class);
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(GetStarted.this, MyLogin.class);
                    startActivity(intent);
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetStarted.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkUserLogin(){

        return false;
    }
}