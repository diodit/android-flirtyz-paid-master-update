package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.FlirtyAdapter;
import com.owo.phlurtyzpaid.model.Emoji;
import com.owo.phlurtyzpaid.model.Flirty;

import java.util.ArrayList;
import java.util.List;

public class FlirtyGroupPage extends AppCompatActivity {
    List<Flirty> flirtyContainer;
    private RecyclerView recyclerView, secondRecycler, thirdRecyclerview;
    private FlirtyAdapter flirtyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flirty_group_page);
        recyclerView = findViewById(R.id.recycler1);
        secondRecycler = findViewById(R.id.recycler2);
        thirdRecyclerview = findViewById(R.id.recycler3);

        flirtyContainer = new ArrayList<>();
        List<Flirty> flirt = flirtGenerator(flirtyContainer);

        flirtyAdapter = new FlirtyAdapter(getApplication(), flirt);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(flirtyAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        secondRecycler.setLayoutManager(linearLayoutManager1);
        secondRecycler.setAdapter(flirtyAdapter);


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        secondRecycler.setLayoutManager(linearLayoutManager2);
        secondRecycler.setAdapter(flirtyAdapter);



    }


    public List<Flirty> flirtGenerator(List<Flirty> flirties){

        for (int i = 0; i < 10; ++i){
            Flirty emoji = new Flirty("https://cdn.pixabay.com/photo/2020/04/26/09/07/bird-5094334__340.jpg", "Adult Emoji", 20);
            flirties.add(emoji);
        }
        return flirties;
    }

    public void bk(View view) {
        finish();
    }

    public void Action(View view) {
        Intent intent =  new Intent(FlirtyGroupPage.this, ActionScreen.class);
        startActivity(intent);
    }
}