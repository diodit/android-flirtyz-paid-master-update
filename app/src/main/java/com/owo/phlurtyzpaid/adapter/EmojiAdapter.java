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
import com.owo.phlurtyzpaid.model.Emoji;

import java.util.List;


public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.Viewholder> {
    Context context;
    List<Emoji> emojisList;
    ProductListener productListener;

    public EmojiAdapter(Context context, List<Emoji> emojisList){
        this.context = context;
        this.emojisList = emojisList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emoji, parent, false);
        return new EmojiAdapter.Viewholder(view);
    }

    public void onSeacrhListerner(ProductListener productListener1){
        this.productListener = productListener1;
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Emoji emoji = emojisList.get(position);

        holder.textView2.setText(String.valueOf(emoji.getPrice()));
        holder.textView.setText(emoji.getGroupName());

        Glide.with(context)
                .asBitmap()
                .circleCrop()
                .load(emoji.getIcon())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return emojisList.size();
    }

   public interface ProductListener{
        void selectProduct( int position);
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView2, textView;
        private LinearLayout linearLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.emoji);
            textView = itemView.findViewById(R.id.flirty);
            linearLayout = itemView.findViewById(R.id.linearlayout);

            textView2 = itemView.findViewById(R.id.price);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (productListener != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            productListener.selectProduct(pos);
                        }
                    }
                }
            });

        }
    }
}
