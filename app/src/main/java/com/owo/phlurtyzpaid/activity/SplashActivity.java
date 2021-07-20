package com.owo.phlurtyzpaid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.core.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 3000;

    private Handler handler;

    private Runnable task = new Runnable() {

        @Override
        public void run() {

            startActivity(new Intent(SplashActivity.this, MainModActivity.class));

            finish();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        handler = new Handler();

        handler.postDelayed(task, SPLASH_TIME_OUT);

    }

    @Override
    protected void onStop() {

        super.onStop();

        handler.removeCallbacks(task);
    }

}
