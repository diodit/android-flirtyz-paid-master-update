package com.owo.phlurtyzpaid.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.MyGridAdapter;
import com.owo.phlurtyzpaid.api.RetrofitClientInstance;
import com.owo.phlurtyzpaid.api.interfaces.ApiCallback;
import com.owo.phlurtyzpaid.api.interfaces.GetForAllCategories;
import com.owo.phlurtyzpaid.api.models.AllCategory;
import com.owo.phlurtyzpaid.core.BaseActivity;
import com.owo.phlurtyzpaid.database.DatabaseHandler;
import com.owo.phlurtyzpaid.model.GridItem;
import com.owo.phlurtyzpaid.utils.ApiEndPoints;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kibrom on 3/20/17.
 */

public class CategoriesActivity extends BaseActivity {

    private Context context;
    private static ArrayList<AllCategory> fetchedData;

    private Activity activity;

    GridView gridView;
    MyGridAdapter myGridAdapter;
    private  final static String URL_API = "";
    ProgressBar simpleProgressBar;

    private String paymentIntentClientSecret;
    private Stripe stripe;
    private RelativeLayout paymentRl;

    private CardInputWidget cardInputWidget;
    private Button payButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        activity = this;

        setContentView(R.layout.activity_categories);
        gridView = (GridView) findViewById(R.id.special_gridview);
        paymentRl = (RelativeLayout) findViewById(R.id.paymentRl);
        paymentRl.setVisibility(View.GONE);

        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        simpleProgressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#e0165a"), android.graphics.PorterDuff.Mode.MULTIPLY);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_Ykta4G0UzBx6fKtALd1z2eK300aPaFzZql"
        );

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dummyResponse();
              //  fetchEmojiData(CategoriesActivity.this, simpleProgressBar); // your code
                pullToRefresh.setRefreshing(false);
            }
        });
//
//        fetchEmojiData(CategoriesActivity.this, simpleProgressBar);

        dummyResponse();


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        // getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        // return true;
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//
//
//        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterAndSearchEmojis(newText);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                filterAndSearchEmojis(query);
//                return true;
//            }
//        };
//
//        searchView.setOnQueryTextListener(queryTextListener);
//        return true;
//    }

//    private void filterAndSearchEmojis (String filterText){
//        if(!filterText.isEmpty()){
//            ArrayList<AllCategory> newList = new ArrayList<>();
//            for(AllCategory newArr : fetchedData){
//                if(newArr.getName().toLowerCase().replace(' ', '_').contains(filterText.toLowerCase().replace(' ', '_'))){
//
//                    newList.add(newArr);
//
//                }
//            }
//            fetchedData = newList;
//            doImageFunctions();
//        }else{
//            fetchEmojiData(CategoriesActivity.this, simpleProgressBar);
//        }
//
//    }

    private void fetchEmojiData(final Context context, final ProgressBar simpleProgressBar) {

        simpleProgressBar.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.INVISIBLE);

        if(!isOnline(this)){
            simpleProgressBar.setVisibility(View.INVISIBLE);
            gridView.setVisibility(View.VISIBLE);
            Toast.makeText(context, "Please check your network connection.", Toast.LENGTH_SHORT).show();
        }else{
            getImagesFromApi(this, new ApiCallback() {
                @Override
                public void onResponse(boolean success) {
                    simpleProgressBar.setVisibility(View.INVISIBLE);
                    gridView.setVisibility(View.VISIBLE);
                    if(success){
                        doImageFunctions();
                    }else{
                        //  otherResponse(simpleProgressBar);
                    }
                }
            });
        }
    }

    public boolean isOnline(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());

    }

    private void doImageFunctions() {

        myGridAdapter = new MyGridAdapter(CategoriesActivity.this, fetchedData);
        gridView.setAdapter(myGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   unlockCategory(fetchedData.get(position),activity);
            }
        });
    }

    public void dummyResponse(){
        // fetchedData = response.body();
        AllCategory allCategory1 = new AllCategory();
        allCategory1.setName("Naked");
        allCategory1.setFile("https://res.cloudinary.com/dcy8sv4bg/image/upload/v1620247791/Flirtyz/folder_image.png");

        AllCategory allCategory2 = new AllCategory();
        allCategory2.setName("Controlled");
        allCategory2.setFile("https://res.cloudinary.com/dcy8sv4bg/image/upload/v1620247791/Flirtyz/folder_image.png");

        AllCategory allCategory3 = new AllCategory();
        allCategory3.setName("Touched");
        allCategory3.setFile("https://res.cloudinary.com/dcy8sv4bg/image/upload/v1620247791/Flirtyz/folder_image.png");

        AllCategory allCategory4 = new AllCategory();
        allCategory4.setName("Reserved");
        allCategory4.setFile("https://res.cloudinary.com/dcy8sv4bg/image/upload/v1620247791/Flirtyz/folder_image.png");

        AllCategory allCategory5 = new AllCategory();
        allCategory5.setName("Sensation");
        allCategory5.setFile("https://res.cloudinary.com/dcy8sv4bg/image/upload/v1620247791/Flirtyz/folder_image.png");

        AllCategory allCategory6 = new AllCategory();
        allCategory6.setName("High Emotions");
        allCategory6.setFile("https://res.cloudinary.com/dcy8sv4bg/image/upload/v1620247791/Flirtyz/folder_image.png");

        ArrayList<AllCategory> imageObjects = new ArrayList<>();
        imageObjects.add(allCategory1);
        imageObjects.add(allCategory2);
        imageObjects.add(allCategory3);
        imageObjects.add(allCategory4);
        imageObjects.add(allCategory5);
        imageObjects.add(allCategory6);

        fetchedData = imageObjects;
        doImageFunctions();
    }

    public static void getImagesFromApi(final Context context, final ApiCallback callback){
        Call<List<AllCategory>> call = RetrofitClientInstance.getRetrofitFlirtyInstance().create(GetForAllCategories.class).getAllData(
                URL_API.concat("api/category/getAll")
        );

        call.enqueue(new Callback<List<AllCategory>>() {
            @Override
            public void onResponse(Call<List<AllCategory>> call, Response<List<AllCategory>> response) {

                if (response.isSuccessful()) {
                    // fetchedData = response.body();
                    ArrayList<AllCategory> imageObjects = new ArrayList<>();
                    for(AllCategory fetchd : response.body()) {
                        AllCategory allCategory = new AllCategory();
                        allCategory.setName(fetchd.getName());
                        allCategory.setFile(URL_API + fetchd.getFile());

                        imageObjects.add(allCategory);
                    }
                    fetchedData = imageObjects;

                    if(response.body().size() < 1){
                        Toast.makeText(context, "No emoji found.", Toast.LENGTH_SHORT).show();
                    }

                    callback.onResponse(response.body() != null);
                }else{
                    Toast.makeText(context, "An error occurred while fetching emojis.", Toast.LENGTH_SHORT).show();
                    callback.onResponse(false);
                }

            }

            @Override
            public void onFailure(Call<List<AllCategory>> call, Throwable t) {
                call.cancel();
                Log.e("error", "onFailure: ",t );
                Toast.makeText(context, "Connection error.", Toast.LENGTH_SHORT).show();
                callback.onResponse(false);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void unlockCategory(AllCategory fetchedImage, final Context context) {
        final String url = fetchedImage.getFile();
        final String imageName = fetchedImage.getName();

        gridView.setVisibility(View.GONE);
        paymentRl.setVisibility(View.VISIBLE);

        cardInputWidget = (CardInputWidget)findViewById(R.id.cardInputWidget);
        payButton = (Button)findViewById(R.id.payButton);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
                if (params != null) {
                    ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                    final Context context = getApplicationContext();
                    stripe = new Stripe(
                            context,
                            PaymentConfiguration.getInstance(context).getPublishableKey()
                    );
                    stripe.confirmPayment(CategoriesActivity.this, confirmParams);
                }
            }
        });

    }


//    private void startCheckout() {
//        // ...
//
//        // Hook up the pay button to the card widget and stripe instance
//        Button payButton = findViewById(R.id.payButton);
//        payButton.setOnClickListener((View view) -> {
//            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
//            if (params != null) {
//                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
//                        .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
//                final Context context = getApplicationContext();
//                stripe = new Stripe(
//                        context,
//                        PaymentConfiguration.getInstance(context).getPublishableKey()
//                );
//                stripe.confirmPayment(this, confirmParams);
//            }
//        });
//    }

    // ...

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));
    }

    // ...

    private static final class PaymentResultCallback implements ApiResultCallback<PaymentIntentResult> {
        @NonNull private final WeakReference<CategoriesActivity> activityRef;

        PaymentResultCallback(@NonNull CategoriesActivity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final CategoriesActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                activity.displayAlert(
                        "Payment completed",
                        gson.toJson(paymentIntent),
                        true
                );
            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed
                activity.displayAlert(
                        "Payment failed",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage(),
                        false
                );
            }
        }

        @Override
        public void onError(@NonNull Exception e) {
            final CategoriesActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            // Payment request failed – allow retrying using the same payment method
            activity.displayAlert("Error", e.toString(), false);
        }
    }


    public void displayAlert(String title, String message, final boolean isSuccess){
        AlertDialog alertDialog = new AlertDialog.Builder(CategoriesActivity.this).create();
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(isSuccess){
                            startActivity(new Intent(CategoriesActivity.this, MainModActivity.class));
                        }else{
                            dialog.dismiss();
                        }

                    }
                });

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }




}
