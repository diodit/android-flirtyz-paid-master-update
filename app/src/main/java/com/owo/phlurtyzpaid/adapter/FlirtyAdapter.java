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
import com.owo.phlurtyzpaid.model.Flirty;

import java.util.List;

public class FlirtyAdapter extends RecyclerView.Adapter<FlirtyAdapter.Viewholder> {
    Context context;
    List<Flirty> flirtyList;

    public FlirtyAdapter(Context context, List<Flirty> flirtyList){
        this.context = context;
        this.flirtyList = flirtyList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flirtydisplay, parent, false);
        return new FlirtyAdapter.Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Flirty flirty = flirtyList.get(position);

        holder.textView1.setText(String.valueOf(flirty.getPrice()));
        holder.textView.setText(flirty.getName());

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(flirty.getIcon())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return flirtyList.size();
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
