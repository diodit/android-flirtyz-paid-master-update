package com.owo.phlurtyzpaid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.model.CathegoryModel;


import java.util.List;

public class FlirtyAdapter extends RecyclerView.Adapter<FlirtyAdapter.Viewholder> {
    Context context;
    private String Url = "http://34.217.126.209/";
    List<CathegoryModel> cathegoryModelList;

    public FlirtyAdapter(Context context, List<CathegoryModel> cathegoryModelList){
        this.context = context;
        this.cathegoryModelList = cathegoryModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flirtydisplay, parent, false);
        return new FlirtyAdapter.Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        CathegoryModel cathegory = cathegoryModelList.get(position);

        holder.textView1.setText(String.valueOf(cathegory.getId()));
        holder.textView.setText(cathegory.getName());

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(Url+cathegory.getFile())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cathegoryModelList.size();
    }



    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView, textView1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.flirtyIcon);
            textView = itemView.findViewById(R.id.flirtyText);
            textView1 = itemView.findViewById(R.id.flirtyPrice);


        }

    }
}
