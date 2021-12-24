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
import com.owo.phlurtyzpaid.model.Emoji;

import java.util.List;


public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.Viewholder> {
    Context context;
    List<CathegoryModel> cathegoryModelList;
    ProductListener productListener;
    private String Url = "http://34.217.126.209/";

    public EmojiAdapter(Context context, List<CathegoryModel> cathegoryModelList){
        this.context = context;
        this.cathegoryModelList = cathegoryModelList;
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

        CathegoryModel cathegory = cathegoryModelList.get(position);

        holder.textView2.setText(String.valueOf(cathegory.getId()));
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
            textView = itemView.findViewById(R.id.emojiText);
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
