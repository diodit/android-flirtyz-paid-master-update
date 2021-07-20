package com.owo.phlurtyzpaid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.MyGridAdapter;
import com.owo.phlurtyzpaid.api.models.AllCategory;
import com.owo.phlurtyzpaid.core.BaseActivity;
import com.owo.phlurtyzpaid.utils.Prefs;
import com.stripe.android.PaymentConfiguration;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class ProfileActivity extends BaseActivity {

    private Context context;
    private Button reg_update;

    private Activity activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        context = getApplicationContext();

        activity = this;

        setContentView(R.layout.activity_profile);

        Prefs prefs = new Prefs(this);

        prefs.open();


        TextView email = findViewById(R.id.email);
        TextView username = findViewById(R.id.username);
        CardView updProfile = findViewById(R.id.updProfile);
        CardView logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutAction();
            }
        });


        updProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUpdate();
            }
        });

//        username.setText(prefs.getString("username"));
//        email.setText(prefs.getString("emailAddress"));
    }

    private void clickUpdate() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.profile_update, null);
        dialog.setView(dialogView);

        reg_update = dialogView.findViewById(R.id.reg_update);

        reg_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();

            }
        });


        dialog.show();


    }


    public void paymentAction(View v){

        startActivity(new Intent(ProfileActivity.this, CategoriesActivity.class));

    }

    public void specialAction(View v){

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        startActivity(browserIntent);

       // startActivity(new Intent(ProfileActivity.this, MainModActivity.class));

    }

    public void logoutAction(){

        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
//        getPrefs().logout();

    }


    public boolean isOnline(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
