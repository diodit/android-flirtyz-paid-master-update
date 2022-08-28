package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.owo.phlurtyzpaid.R;

import com.owo.phlurtyzpaid.adapter.FlirtyAdapter;
import com.owo.phlurtyzpaid.model.CathegoryModel;

import com.owo.phlurtyzpaid.service.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlirtyGroupPage extends AppCompatActivity {
    List<CathegoryModel> cathegoryMod;
    private RecyclerView recyclerView, secondRecycler, thirdRecyclerview;
    private FlirtyAdapter flirtyAdapter;
    private double price;
    //private String folderName;
    String image_two, image_one, groupName,image_three,image_four;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flirty_group_page);
        Toolbar toolbar = findViewById(R.id.back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Flirty Group");
        Button btn_purchase = findViewById(R.id.btn_purchase);


        ImageView imageView1 = findViewById(R.id.imageone);
        ImageView imageView2 = findViewById(R.id.imagetwo);
        ImageView imageView3 = findViewById(R.id.imagethree);
        ImageView imageView4 = findViewById(R.id.imagefour);
        TextView textView3 = findViewById(R.id.textView3);
        TextView groupIdbyName = findViewById(R.id.groupidbyName);


        image_one = getIntent().getStringExtra("imageone");

        image_two = getIntent().getStringExtra("imagetwo");

        image_three = getIntent().getStringExtra("imagethree");

        image_four = getIntent().getStringExtra("imagefour");

        price = getIntent().getDoubleExtra("price",0);
        groupName = getIntent().getStringExtra("group_name");
        groupIdbyName.setText(groupName);
//       secondRecycler = findViewById(R.id.recycler2);
//       thirdRecyclerview = findViewById(R.id.recycler3);



        if(image_one != null && image_two != null){
            Glide.with(this).load(image_one).into(imageView1);
            Glide.with(this).load(image_two).into(imageView2);
            textView3.setText("$"+price);
        }

        if(image_three != null && image_four != null){
            Glide.with(this).load(image_three).into(imageView3);
            Glide.with(this).load(image_four).into(imageView4);

        }





        cathegoryMod = new ArrayList<>();
        secondScreen();


//        folderName = getIntent().getStringExtra("folderName");
        btn_purchase.setText("Purchase for $"+price);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    private void secondScreen(){

        Call<List<CathegoryModel>> listCall = ApiClient.getService().getCathegorieModel();
        listCall.enqueue(new Callback<List<CathegoryModel>>() {
            @Override
            public void onResponse(Call<List<CathegoryModel>> call, Response<List<CathegoryModel>> response) {
                if (!response.isSuccessful()){


                    Log.d("not successfuly", ""+response.errorBody());

                    return;

                }

                cathegoryMod = response.body();

                cathegoryMod.get(0).getId();

                recyclerView = findViewById(R.id.recycler1);

                Log.d("view", ""+cathegoryMod.size());
                flirtyAdapter = new FlirtyAdapter(FlirtyGroupPage.this, cathegoryMod);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//                recyclerView.setLayoutManager(linearLayoutManager);
//                recyclerView.setAdapter(flirtyAdapter);


//                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplication());
//                linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
//                secondRecycler.setLayoutManager(linearLayoutManager1);
//                secondRecycler.setAdapter(flirtyAdapter);
//
//
//                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplication());
//                linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
//                secondRecycler.setLayoutManager(linearLayoutManager2);
//                secondRecycler.setAdapter(flirtyAdapter);



            }

            @Override
            public void onFailure(Call<List<CathegoryModel>> call, Throwable t) {
                Log.d("cathenotshown", ""+t.getMessage());
            }
        });
    }
    public void bk(View view) {
        finish();
    }

    public void Action(View view) {
        Intent intent =  new Intent(FlirtyGroupPage.this, CheckoutActivity.class);
        intent.putExtra("price", price);
        intent.putExtra("group_name",groupName);
        intent.putExtra("imageone", image_one);
        intent.putExtra("imagetwo", image_two);
        intent.putExtra("imagethree",image_three);
        intent.putExtra("imagefour",image_four);
        intent.putExtra("folder",getIntent().getStringExtra("folderName"));
        startActivity(intent);
    }
}