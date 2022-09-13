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

    private String folderName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Button cancel = findViewById(R.id.Button02);
        Button checkout = findViewById(R.id.Button03);
        TextView totalFee = findViewById(R.id.totalfee);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.groupidbyName);
        TextView creator = findViewById(R.id.creator);

        String image_one = getIntent().getStringExtra("imageone");
        String image_two = getIntent().getStringExtra("imagetwo");
        String image_three = getIntent().getStringExtra("imagethree");
        String image_four = getIntent().getStringExtra("imagefour");
        double price = getIntent().getDoubleExtra("price",0);


        folderName = getIntent().getStringExtra("folder");

        String groupName = getIntent().getStringExtra("group_name");
        creator.setText(getIntent().getStringExtra("full_name"));
        ImageView imageView1 = findViewById(R.id.imageone);
        Glide.with(this).load(image_one).into(imageView1);
        textView3.setText("$"+price);
        textView4.setText(groupName);


        if(price != 0){
            totalFee.setText("Total Fee: $"+totalFee(price));
        }

        if(image_two != null){

            ImageView imageView2 = findViewById(R.id.imagetwo);
            Glide.with(this).load(image_two).into(imageView2);

        }


        if(image_three != null){
            ImageView imageView3 = findViewById(R.id.imagethree);
            Glide.with(this).load(image_three).into(imageView3);
        }

        if(image_four != null){
            ImageView imageView4 = findViewById(R.id.imagefour);
            Glide.with(this).load(image_four).into(imageView4);
        }





        cancel.setOnClickListener(view -> {
            Intent intent = new Intent(CheckoutActivity.this, FlirtyGroupPage.class);
            startActivity(intent);
        });

        checkout.setOnClickListener(view -> {
            String originalString = ApiEndPoints.paymentLink;
            int real_price = (int) (price * 100);
            String finalUrl =  appendUri(originalString, folderName,String.valueOf(real_price));
            Log.d("finalUrl",finalUrl);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl)));
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