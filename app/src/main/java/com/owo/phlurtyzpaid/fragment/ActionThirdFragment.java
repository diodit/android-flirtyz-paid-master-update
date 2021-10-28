package com.owo.phlurtyzpaid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.PurchaseAdapter;
import com.owo.phlurtyzpaid.model.Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActionThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Purchase> purchasesContainer;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PurchaseAdapter purchaseAdapter;
    private RecyclerView recyclerView;


    public ActionThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionThirdFragment newInstance(String param1, String param2) {
        ActionThirdFragment fragment = new ActionThirdFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_action_third, container, false);

        purchasesContainer = new ArrayList<>();
        List<Purchase> purchase = purchasesGenerator(purchasesContainer);
        recyclerView = view.findViewById(R.id.recycler12);

        purchaseAdapter = new PurchaseAdapter(getContext(), purchase);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(purchaseAdapter);

        return view;

    }
    public List<Purchase> purchasesGenerator (List<Purchase> purchases){
//        emojiContainer = new ArrayList<>();


//        emojis = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            Purchase purchase1 = new Purchase("https://cdn.pixabay.com/photo/2020/04/26/09/07/bird-5094334__340.jpg", "group name");
            purchases.add(purchase1);
        }

        return purchases;
    }
}