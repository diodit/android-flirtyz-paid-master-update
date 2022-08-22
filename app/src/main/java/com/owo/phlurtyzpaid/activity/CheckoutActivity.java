package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;

import java.net.URI;
import java.net.URISyntaxException;

public class CheckoutActivity extends AppCompatActivity {

    private Button cancel, checkout;
    private TextView totalFee;
    private String folderName;
    private ImageView imageView1, imageView2;
    private TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        cancel= findViewById(R.id.Button02);
        checkout = findViewById(R.id.Button03);
        totalFee = findViewById(R.id.totalfee);
        textView3 = findViewById(R.id.textView3);
        double price = getIntent().getDoubleExtra("price",0);
        folderName = getIntent().getStringExtra("folder");

        String image_one = getIntent().getStringExtra("imageone");
        String image_two = getIntent().getStringExtra("imagetwo");


        Log.d("image_one", image_one);
        Log.d("image_two", image_two);
        if(price != 0){
            totalFee.setText("Total Fee: $"+totalFee(price));
        }

        if(image_one != null && image_two != null){
            imageView1 = findViewById(R.id.imageone);
            Glide.with(this).load(image_one).into(imageView1);
            imageView2 = findViewById(R.id.imagetwo);
            Glide.with(this).load(image_two).into(imageView2);
            textView3.setText("$"+price);
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