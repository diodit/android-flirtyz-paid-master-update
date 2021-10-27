package com.owo.phlurtyzpaid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.owo.phlurtyzpaid.R;

import java.util.zip.Inflater;

public class MakePayment extends AppCompatActivity {

    private Toolbar toolbarr;
    private Button paymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        toolbarr = findViewById(R.id.toolbar11);
        paymentButton = findViewById(R.id.payment);


        toolbarr.inflateMenu(R.menu.makepayment);


        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent(MakePayment.this, PaymentDesign.class);
                startActivity(intent);
                Log.d("shiw", "myomy");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//         super.onCreateOptionsMenu(menu);

//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.makepayment, menu);
        getMenuInflater().inflate(R.menu.makepayment, menu);

        toolbarr.inflateMenu(R.menu.makepayment);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    public void cancle(View view) {
        finish();
    }



    public void back(View view) {
        finish();
    }
}