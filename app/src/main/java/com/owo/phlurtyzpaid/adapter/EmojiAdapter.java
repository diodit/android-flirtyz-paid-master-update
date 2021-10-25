package com.owo.phlurtyzpaid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.owo.phlurtyzpaid.R;


public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.Viewholder> {
    Context context;


    public EmojiAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emoji, parent, false);
        return new EmojiAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.textView2.getText();
        holder.textView.getText();
        holder.textView1.getText();
        holder.imageView.getDrawable();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView, textView1, textView2;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.emoji);
            textView = itemView.findViewById(R.id.flirty);
            textView1 = itemView.findViewById(R.id.group);
            textView2 = itemView.findViewById(R.id.price);

        }
    }
}
