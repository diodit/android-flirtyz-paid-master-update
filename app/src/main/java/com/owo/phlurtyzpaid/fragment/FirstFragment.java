package com.owo.phlurtyzpaid.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.activity.MakePayment;
import com.owo.phlurtyzpaid.adapter.EmojiAdapter;
import com.owo.phlurtyzpaid.model.Cathegory;
import com.owo.phlurtyzpaid.model.CathegoryModel;
import com.owo.phlurtyzpaid.model.Emoji;
import com.owo.phlurtyzpaid.service.ApiClient;
import com.stripe.android.model.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EmojiAdapter emojiAdapter;
    private RecyclerView recycler;
//    List<Emoji> emojiContainer;
    private  View view;
    private  List<CathegoryModel> cathegoryMod;


    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            //        recycler = view.findViewById(R.id.recycler);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);


//        emojiContainer = new ArrayList<>();
        cathegoryMod = new ArrayList<>();

        secondScreen();

//        List<Emoji> emoji = emojisGenerator(emojiContainer);
//        recycler = view.findViewById(R.id.recycler);


        if (emojiAdapter != null){
            emojiAdapter.onSeacrhListerner(new EmojiAdapter.ProductListener() {
                @Override
                public void selectProduct(int position) {

                    Intent intent = new Intent(getContext(), MakePayment.class);
                    startActivity(intent);
                }
            });
        }

        return view;
    }

//    public List<Emoji> emojisGenerator(List<Emoji> emojis){
////        emojiContainer = new ArrayList<>();
//
//
////        emojis = new ArrayList<>();
//        for (int i = 0; i < 10; ++i){
//            Emoji emoji = new Emoji("https://cdn.pixabay.com/photo/2020/04/26/09/07/bird-5094334__340.jpg", "group name", 20);
//            emojis.add(emoji);
//        }
//
//        return emojis;
//    }


    private void secondScreen(){

        Call<List<CathegoryModel>> listCall =ApiClient.getService().getCathegorieModel();
        listCall.enqueue(new Callback<List<CathegoryModel>>() {
            @Override
            public void onResponse(Call<List<CathegoryModel>> call, Response<List<CathegoryModel>> response) {
                if (!response.isSuccessful()){


                    Log.d("not successfuly", ""+response.errorBody());

                    return;

                }

                cathegoryMod = response.body();

                cathegoryMod.get(0).getId();

                recycler = view.findViewById(R.id.recycler);

                Log.d("view", ""+cathegoryMod.size());
                emojiAdapter = new EmojiAdapter(getContext(), cathegoryMod);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                recycler.setAdapter(emojiAdapter);
            }

            @Override
            public void onFailure(Call<List<CathegoryModel>> call, Throwable t) {

                Log.d("cathenotshown", ""+t.getMessage());
            }
        });
    }

//    private void firstScreen(){
//        Call<Cathegory> cathegoryCall = ApiClient.getService().getCathegory();
//        cathegoryCall.enqueue(new Callback<Cathegory>() {
//            @Override
//            public void onResponse(Call<Cathegory> call, Response<Cathegory> response) {
//                if (!response.isSuccessful()){
//
//
//                    Log.d("not successfuly", ""+response.errorBody());
//
//                    return;
//
//                }
//
//                cathegoryMod = response.body().getCathegoryModels();
//
//                cathegoryMod.get(0).getId();
//
//                recycler = view.findViewById(R.id.recycler);
//
//                Log.d("view", ""+cathegoryMod.size());
//                emojiAdapter = new EmojiAdapter(getContext(), cathegoryMod);
//                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
//                recycler.setAdapter(emojiAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<Cathegory> call, Throwable t) {
//                Log.d("cathenotshown", ""+t.getMessage());
//
//
//
//            }
//        });
    //}

//    private List<CathegoryModel> listCathegorieModel(){
//        firstScreen();
//        return null;
//    }
}
