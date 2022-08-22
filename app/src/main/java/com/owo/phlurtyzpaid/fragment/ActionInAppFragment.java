package com.owo.phlurtyzpaid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.activity.FlirtyGroupPage;
import com.owo.phlurtyzpaid.api.RetrofitClientInstance;
import com.owo.phlurtyzpaid.api.interfaces.ApiCallback;
import com.owo.phlurtyzpaid.api.interfaces.GetForAllCategories;
import com.owo.phlurtyzpaid.api.models.AllCategory;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActionInAppFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionInAppFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ArrayList<AllCategory> fetchedData;
    private ImageView imageView1, imageView2;
    private TextView textView3;
    private LinearLayout firstlayoutaction;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActionInAppFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionInAppFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionInAppFragment newInstance(String param1, String param2) {
        ActionInAppFragment fragment = new ActionInAppFragment();
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

        fetchedData = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_action_in_app, container, false);
        imageView1 = view.findViewById(R.id.imageone);
        imageView2 = view.findViewById(R.id.imagetwo);
        textView3 = view.findViewById(R.id.textView3);
        firstlayoutaction = view.findViewById(R.id.firstlayoutaction);

        firstlayoutaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fetchedData.size()<1){
                }else{
                    Intent intent = new Intent(getContext(), FlirtyGroupPage.class);
                    intent.putExtra("price",fetchedData.get(0).getPrice());
                    intent.putExtra("folderName",fetchedData.get(0).getFolderName());
                    intent.putExtra("imageone","http://34.213.79.205/"+fetchedData.get(0).getFile());
                    intent.putExtra("imagetwo","http://34.213.79.205/"+fetchedData.get(1).getFile());
                    startActivity(intent);
                }
            }
        });
        getInAppGroup(getContext());
        return view;
    }

    public void getInAppGroup(final Context context){
//        Call<List<AllCategory>> call = RetrofitClientInstance.getRetrofitFlirtyInstance().create(GetForAllCategories.class).getInApp(ApiEndPoints.CategoryByGroupApp);


        Call<List<AllCategory>> call = RetrofitClientInstance.getRetrofitFlirtyInstance().create(GetForAllCategories.class).getInApp(ApiEndPoints.CategoryByGroupApp);

        call.enqueue(new retrofit2.Callback<List<AllCategory>>(){

            @Override
            public void onResponse(Call<List<AllCategory>> call, Response<List<AllCategory>> response) {
                if(response.isSuccessful()){
                    ArrayList<AllCategory> imageObjects = new ArrayList<>();

                    for(AllCategory fetchd : response.body()) {
                        AllCategory allCategory = new AllCategory();
                        allCategory.setName(fetchd.getName());
                        allCategory.setId(fetchd.getId());
                        allCategory.setFile(fetchd.getFile());
                        allCategory.setPrice(fetchd.getPrice()/100);
                        allCategory.setFolderName(fetchd.getFolderName());
                        imageObjects.add(allCategory);
                    }

                    fetchedData = imageObjects;

                            Glide.with(context)
                            .load("http://34.213.79.205/"+fetchedData.get(0).getFile())
                            .into(imageView1);

                            Log.d("URLIMAGE","http://34.213.79.205/"+fetchedData.get(0).getFile());


                    Glide.with(context)
                            .load("http://34.213.79.205/"+fetchedData.get(1).getFile())
                            .into(imageView2);


                    textView3.setText("$"+(fetchedData.get(1).getPrice()));

                    Log.d("InApp",String.valueOf(fetchedData.size()));

                    if(response.body().size() < 1){
                        Toast.makeText(context, "No categories found.", Toast.LENGTH_SHORT).show();
                    }

//                    callback.onResponse(response.body() != null);


                }else{
                    Toast.makeText(context, "An error occurred while fetching categories." + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//                    callback.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<List<AllCategory>> call, Throwable t) {
                call.cancel();
                Log.e("error", "onFailure: ",t );
                Toast.makeText(context, "Connection Error.", Toast.LENGTH_SHORT).show();
//                callback.onResponse(false);
            }
        });

//        call.enqueue(new retrofit2.Callback<List<AllCategory>>() {
//            @Override
//            public void onResponse(Call<List<AllCategory>> call, Response<List<AllCategory>> response) {
//
//                if (response.isSuccessful()) {
//
//                    ArrayList<AllCategory> imageObjects = new ArrayList<>();
//
//                    for(AllCategory fetchd : response.body()) {
//                        AllCategory allCategory = new AllCategory();
//                        allCategory.setName(fetchd.getName());
//                        allCategory.setId(fetchd.getId());
//
//                        imageObjects.add(allCategory);
//                    }
//                    fetchedData = imageObjects;
//
//
//                    if(response.body().size() < 1){
//                        Toast.makeText(context, "No categories found.", Toast.LENGTH_SHORT).show();
//                    }
//
//                    callback.onResponse(response.body() != null);
//                }else{
//                    Toast.makeText(context, "An error occurred while fetching categories." + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//                    callback.onResponse(false);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<AllCategory>> call, Throwable t) {
//                call.cancel();
//                Log.e("error", "onFailure: ",t );
//                Toast.makeText(context, "Connection Error.", Toast.LENGTH_SHORT).show();
//                callback.onResponse(false);
//            }
//        });

    }
}