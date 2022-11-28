package com.owo.phlurtyzpaid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.activity.FlirtyGroupPage;
import com.owo.phlurtyzpaid.adapter.InAppActionAdapter;
import com.owo.phlurtyzpaid.api.RetrofitClientInstance;
import com.owo.phlurtyzpaid.api.interfaces.GetForAllCategories;
import com.owo.phlurtyzpaid.api.models.AllCategory;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InAppCategoryFragment6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InAppCategoryFragment6 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private ProgressBar progressBar;
    private String categoryId;

    private RecyclerView recyclerView;
    private InAppActionAdapter inAppActionAdapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InAppCategoryFragment6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InAppCategoryFragment6.
     */
    // TODO: Rename and change types and number of parameters
    public static InAppCategoryFragment6 newInstance(String param1, String param2) {
        InAppCategoryFragment6 fragment = new InAppCategoryFragment6();
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

        categoryId = this.getArguments().getString("categoryId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_app, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerview);
        getInAppGroup(getContext());
        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_in_app_category6, container, false);
    }

    public void getInAppGroup(final Context context) {


        Call<List<AllCategory>> call = RetrofitClientInstance.getRetrofitFlirtyInstance().create(GetForAllCategories.class).getInApp(ApiEndPoints.CategoryByGroupApp+categoryId);

        call.enqueue(new retrofit2.Callback<List<AllCategory>>() {

            @Override
            public void onResponse(Call<List<AllCategory>> call, Response<List<AllCategory>> response) {
                if (response.isSuccessful()) {
                    ArrayList<AllCategory> imageObjects = new ArrayList<>();

                    for (AllCategory fetchd : response.body()) {
                        AllCategory allCategory = new AllCategory();
                        allCategory.setName(fetchd.getName());
                        allCategory.setId(fetchd.getId());
                        allCategory.setFile(fetchd.getFile());
                        allCategory.setPrice(fetchd.getPrice() / 100);
                        allCategory.setFolderName(fetchd.getFolderName());
                        allCategory.setEmojiModel(fetchd.getEmojiModel());
                        allCategory.setCreatedBy(fetchd.getCreatedBy());
                        imageObjects.add(allCategory);
                    }
                    progressBar.setVisibility(View.GONE);
                    inAppActionAdapter = new InAppActionAdapter(imageObjects, getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(inAppActionAdapter);

                    inAppActionAdapter.setListenerForAdapter(position -> {
                        Intent intent = new Intent(getContext(), FlirtyGroupPage.class);
                        AllCategory category = imageObjects.get(position);
                        intent.putExtra("price", category.getPrice());
                        intent.putExtra("folderName", category.getFolderName());
                        intent.putExtra("group_name", category.getName());
                        intent.putExtra("createdByInfo",category.getCreatedBy());
                        intent.putExtra("categoryId",categoryId);
                        if (category.getEmojiModel().size() > 0) {
                            intent.putExtra("imageone", "http://34.213.79.205/" + category.getEmojiModel().get(0).getFile());
                        }


                        if (category.getEmojiModel().size() > 1) {
                            intent.putExtra("imagetwo", "http://34.213.79.205/" + category.getEmojiModel().get(1).getFile());
                        }

                        if (category.getEmojiModel().size() > 2) {
                            intent.putExtra("imagethree", "http://34.213.79.205/" + category.getEmojiModel().get(2).getFile());
                        }
                        if (category.getEmojiModel().size() > 3) {
                            intent.putExtra("imagefour", "http://34.213.79.205/" + category.getEmojiModel().get(3).getFile());
                        }


                        startActivity(intent);
                    });


                    if (response.body().size() < 1) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "No categories found.", Toast.LENGTH_SHORT).show();
                    }

//                    callback.onResponse(response.body() != null);


                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "An error occurred while fetching categories." + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//                    callback.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<List<AllCategory>> call, Throwable t) {
                call.cancel();
                progressBar.setVisibility(View.GONE);
                Log.e("error", "onFailure: ", t);
                Toast.makeText(context, "Connection Error.", Toast.LENGTH_SHORT).show();
//                callback.onResponse(false);
            }
        });


    }
}