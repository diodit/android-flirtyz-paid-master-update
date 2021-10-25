package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.owo.phlurtyzpaid.R;

public class MyLogin extends AppCompatActivity {
    TextView backLogin, register;
    MaterialButton myLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        backLogin = findViewById(R.id.back);
        register = findViewById(R.id.signUp);
        myLogin = findViewById(R.id.login);


        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLogin.this, GetStarted.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLogin.this, Register.class);
                startActivity(intent);
            }
        });


        myLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLogin.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });
    }
}