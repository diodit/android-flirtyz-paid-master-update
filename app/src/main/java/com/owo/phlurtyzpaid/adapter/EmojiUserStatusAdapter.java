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
import com.owo.phlurtyzpaid.api.models.UserStatus;

import java.util.List;

public class EmojiUserStatusAdapter extends RecyclerView.Adapter<EmojiUserStatusAdapter.EmojiHolder> {

    Context context;
    List<UserStatus> userStatusList;
    EmojiAdapter.ProductListener productListener;
    private String Url = "http://34.217.126.209/";


    public EmojiUserStatusAdapter(Context context, List<UserStatus> userStatusList) {
        this.context = context;
        this.userStatusList = userStatusList;

    }

    @NonNull
    @Override
    public EmojiUserStatusAdapter.EmojiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emoji, parent, false);
        return new EmojiUserStatusAdapter.EmojiHolder(view);
    }

    public void onSeacrhListerner(EmojiAdapter.ProductListener productListener1){
        this.productListener = productListener1;
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiUserStatusAdapter.EmojiHolder holder, int position) {

        UserStatus userStatus = userStatusList.get(position);
        String dollarSign ="$";

        holder.textView2.setText(String.valueOf(dollarSign+userStatus.getPrice()));
        holder.textView.setText(userStatus.getName());

        if (userStatus.getEmojis().size() > 0){
            Glide.with(context)
                    .asBitmap()
                    .circleCrop()
                    .load(Url+userStatus.getEmojis().get(0).getFile())
                    .into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return userStatusList.size();
    }

    public interface ProductListener{
        void selectProduct( int position);
    }


    public class EmojiHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView2, textView;
        private LinearLayout linearLayout;


        public EmojiHolder(@NonNull View itemView) {
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
