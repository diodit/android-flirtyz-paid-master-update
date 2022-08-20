package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;

import java.net.URI;
import java.net.URISyntaxException;

public class CheckoutActivity extends AppCompatActivity {

    private Button cancel, checkout;
    private TextView totalFee;
    private String folderName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        cancel= findViewById(R.id.Button02);
        checkout = findViewById(R.id.Button03);
        totalFee = findViewById(R.id.totalfee);
        double price = getIntent().getDoubleExtra("price",0);
        folderName = getIntent().getStringExtra("folder");

        if(price != 0){
            totalFee.setText("Total Fee: $"+totalFee(price));
        }






        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, FlirtyGroupPage.class);
                startActivity(intent);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String originalString = ApiEndPoints.paymentLink;
                int real_price = (int) (price * 100);
                String finalUrl =  appendUri(originalString, folderName,String.valueOf(real_price));
                Log.d("finalUrl",finalUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl)));

                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ApiEndPoints.PaymentURL)));
            }
        });


    }


    private double totalFee(double price){
//        return price + 1.99;
        return price;
    }

    public static String appendUri(String oldUri, String folderName,String price)  {
        return oldUri+folderName+"&amount="+price;
    }


}