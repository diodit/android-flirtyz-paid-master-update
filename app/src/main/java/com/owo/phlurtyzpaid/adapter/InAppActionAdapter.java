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
import com.owo.phlurtyzpaid.api.models.AllCategory;

import java.util.List;

public class InAppActionAdapter extends RecyclerView.Adapter<InAppActionAdapter.InAppActionAdapterViewHolder> {
    private final List<AllCategory> allCategoryList;
    private final Context context;
    private InAppActionAdapterListener listener;
    public InAppActionAdapter( List<AllCategory> allCategoryList, Context context){
        this.allCategoryList = allCategoryList;
        this.context = context;
    }
    @NonNull
    @Override
    public InAppActionAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inappactionsingle,parent,false);
        return new InAppActionAdapterViewHolder(view);
    }


    public void setListenerForAdapter(InAppActionAdapterListener listener){
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull InAppActionAdapterViewHolder holder, int position) {

        AllCategory allCategory = allCategoryList.get(position);
        holder.price.setText("$"+String.valueOf(allCategory.getPrice()));
        Glide.with(context)
                .load("http://34.213.79.205/"+allCategory.getFile())
                .into(holder.imageone);

        Glide.with(context)
                .load("http://34.213.79.205/"+allCategory.getFile())
                .into(holder.imagetwo);


    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public class InAppActionAdapterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageone,imagetwo;
        TextView price;
        public InAppActionAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.firstlayoutaction);
            imageone = itemView.findViewById(R.id.imageone);
            imagetwo = itemView.findViewById(R.id.imagetwo);
            price = itemView.findViewById(R.id.textView3);

            linearLayout.setOnClickListener(view -> {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.navigate(position);
                    }
                }
            });

        }
    }



    public interface  InAppActionAdapterListener{
        void navigate(int position);
    }

}
