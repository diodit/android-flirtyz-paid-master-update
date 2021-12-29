package com.owo.phlurtyzpaid.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.activity.MakePayment;
import com.owo.phlurtyzpaid.activity.MyLogin;
import com.owo.phlurtyzpaid.adapter.EmojiAdapter;
import com.owo.phlurtyzpaid.adapter.EmojiUserStatusAdapter;
import com.owo.phlurtyzpaid.api.models.UserStatus;

import com.owo.phlurtyzpaid.service.SharedPref;
import com.owo.phlurtyzpaid.utils.GeneralFactory;
import com.stripe.android.model.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

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
    private EmojiUserStatusAdapter emojiAdapter;
    private RecyclerView recycler;
    private String token;
    private  View view;
    private List<UserStatus> statuses;


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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_first, container, false);

        token =  SharedPref.getInstance(getContext()).getStoredToken();

        Log.d("token", ""+token);
        GeneralFactory generalFactory = GeneralFactory.getGeneralFactory(getActivity());


        statuses = new ArrayList<>();

                generalFactory.loadFromApiUserStatus(token, new GeneralFactory.FetchUserStatus() {
                    @Override
                    public void userFetcher(List<UserStatus> friends) {

                        recycler = view.findViewById(R.id.recycler);
                        statuses = friends;
                        Log.d("view", ""+statuses.size());
                        emojiAdapter = new EmojiUserStatusAdapter(getContext(), statuses);
                        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                        recycler.setAdapter(emojiAdapter);
                    }
                });


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

}
