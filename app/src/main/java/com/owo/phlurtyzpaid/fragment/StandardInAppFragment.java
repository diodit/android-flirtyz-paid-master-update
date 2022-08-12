package com.owo.phlurtyzpaid.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.activity.FlirtyGroupPage;
import com.owo.phlurtyzpaid.activity.MakePayment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StandardInAppFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandardInAppFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StandardInAppFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StandardInAppFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StandardInAppFragment newInstance(String param1, String param2) {
        StandardInAppFragment fragment = new StandardInAppFragment();
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
        View view =  inflater.inflate(R.layout.fragment_standard_in_app, container, false);
        LinearLayout linearLayout1 = view.findViewById(R.id.firstlayout);
        LinearLayout linearLayout2 = view.findViewById(R.id.seclayout);
        LinearLayout linearLayout3 = view.findViewById(R.id.thirdlayou);
        LinearLayout linearLayout4 = view.findViewById(R.id.fourthlayou);


        linearLayout1.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), FlirtyGroupPage.class);
            startActivity(intent);
        });

        linearLayout2.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), FlirtyGroupPage.class);
            startActivity(intent);
        });


        linearLayout3.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), FlirtyGroupPage.class);
            startActivity(intent);
        });

        linearLayout4.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), FlirtyGroupPage.class);
            startActivity(intent);
        });
        return view;
    }
}