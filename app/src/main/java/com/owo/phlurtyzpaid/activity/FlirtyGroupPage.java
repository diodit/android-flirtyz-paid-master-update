package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.owo.phlurtyzpaid.R;

import com.owo.phlurtyzpaid.adapter.FlirtyAdapter;
import com.owo.phlurtyzpaid.adapter.InAppActionAdapter;
import com.owo.phlurtyzpaid.api.RetrofitClientInstance;
import com.owo.phlurtyzpaid.api.interfaces.GetForAllCategories;
import com.owo.phlurtyzpaid.api.models.AllCategory;
import com.owo.phlurtyzpaid.api.models.CreatedBy;
import com.owo.phlurtyzpaid.model.CathegoryModel;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class FlirtyGroupPage extends AppCompatActivity {
    List<CathegoryModel> cathegoryMod;
    private RecyclerView recyclerView;

    private double price;
    private ProgressBar progressBar;
    //private String folderName;

    private InAppActionAdapter inAppActionAdapter;
    String image_two, image_one, groupName,image_three,image_four;

    private CreatedBy createdBy;


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
        TextView groupMemberName = findViewById(R.id.groupMemberName);

        TextView creatorsName = findViewById(R.id.creatorsname);
        TextView groupIdbyName = findViewById(R.id.groupidbyName);

        progressBar = findViewById(R.id.progress_bar);
        image_one = getIntent().getStringExtra("imageone");

        image_two = getIntent().getStringExtra("imagetwo");

        image_three = getIntent().getStringExtra("imagethree");

        image_four = getIntent().getStringExtra("imagefour");
        createdBy = (CreatedBy) getIntent().getSerializableExtra("createdByInfo");




        price = getIntent().getDoubleExtra("price",0);
        groupName = getIntent().getStringExtra("group_name");
        groupIdbyName.setText(groupName);
        recyclerView = findViewById(R.id.recyclerview);

        Log.d("ImageOe",image_one);
        Log.d("Price",""+price);

        Glide.with(this).load(image_one).into(imageView1);
        textView3.setText("$"+price);

        if(createdBy != null){
            creatorsName.setText(createdBy.getFirstName()+" "+createdBy.getLastName());
            groupMemberName.setText(createdBy.getFirstName()+" "+createdBy.getLastName());
        }
        if(image_one != null && image_two != null){
            Glide.with(this).load(image_two).into(imageView2);
        }

        if(image_three != null && image_four != null){
            Glide.with(this).load(image_three).into(imageView3);
            Glide.with(this).load(image_four).into(imageView4);

        }



        getInAppGroup(this);

        cathegoryMod = new ArrayList<>();

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



    public void getInAppGroup(final Context context) {


        Call<List<AllCategory>> call = RetrofitClientInstance.getRetrofitFlirtyInstance().create(GetForAllCategories.class).getInApp(ApiEndPoints.CategoryByGroupApp);

        call.enqueue(new retrofit2.Callback<List<AllCategory>>() {

            @Override
            public void onResponse(Call<List<AllCategory>> call, Response<List<AllCategory>> response) {
                if (response.isSuccessful()) {
                    ArrayList<AllCategory> imageObjects = new ArrayList<>();

                    for (AllCategory fetchd : response.body()) {

                        if(!Objects.equals(fetchd.getName(), groupName)){
                            AllCategory allCategory = new AllCategory();
                            allCategory.setName(fetchd.getName());
                            allCategory.setId(fetchd.getId());
                            allCategory.setFile(fetchd.getFile());
                            allCategory.setPrice(fetchd.getPrice() / 100);
                            allCategory.setFolderName(fetchd.getFolderName());
                            allCategory.setEmojiModel(fetchd.getEmojiModel());
                            imageObjects.add(allCategory);
                        }



                    }


                    progressBar.setVisibility(View.GONE);

                    inAppActionAdapter = new InAppActionAdapter(imageObjects, FlirtyGroupPage.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());

                    if( recyclerView != null){
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(inAppActionAdapter);
                    }


                    if (response.body().size() < 1) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "No categories found.", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "An error occurred while fetching categories." + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//
                }
            }

            @Override
            public void onFailure(Call<List<AllCategory>> call, Throwable t) {
                call.cancel();
                progressBar.setVisibility(View.GONE);
                Log.e("error", "onFailure: ", t);
                Toast.makeText(context, "Connection Error.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}