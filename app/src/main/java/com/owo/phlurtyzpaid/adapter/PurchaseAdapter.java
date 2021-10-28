package com.owo.phlurtyzpaid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.model.Purchase;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.Viewholder> {

    Context context;
    List<Purchase> purchaseList;

    public  PurchaseAdapter(Context context, List<Purchase> purchaseList){
        this.context = context;
        this.purchaseList = purchaseList;
    }

    @NonNull
    @Override
    public PurchaseAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payemtdesign, parent, false);
        return new PurchaseAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter.Viewholder holder, int position) {
        Purchase purchase = purchaseList.get(position);
        holder.textView.setText(purchase.getName());

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(purchase.getIcon())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.purchaseIcon);
            textView = itemView.findViewById(R.id.purchasText);
        }
    }
}
