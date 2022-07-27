package com.owo.phlurtyzpaid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.core.BaseActivity;
import com.owo.phlurtyzpaid.utils.Prefs;

import org.apache.commons.lang3.StringUtils;

public class LoginActivity extends BaseActivity {

    private Context context;
    private Activity activity_login;



    TextInputEditText username, password, reg_username, reg_password,
            reg_firstName, reg_lastName, reg_email, reg_confirmemail;
    Button login, signUp, reg_register;
    TextInputLayout txtInLayoutUsername, txtInLayoutPassword, txtInLayoutRegPassword;
    CheckBox rememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);
        txtInLayoutUsername = findViewById(R.id.txtInLayoutUsername);
        txtInLayoutPassword = findViewById(R.id.txtInLayoutPassword);
//        rememberMe = findViewById(R.id.rememberMe);


        Prefs prefs = new Prefs(this);
        prefs.open();
        if(prefs.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
        }


        ClickLogin();


        //SignUp's Button for showing registration page
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSignUp();
            }
        });


    }

    //This is method for doing operation of check login
    private void ClickLogin() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().trim().isEmpty()) {

                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                    txtInLayoutUsername.setError("Username should not be empty");
                } else {
                    //Here you can write the codes for checking username
                    txtInLayoutUsername.setError(null);
                }
                if (password.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                    txtInLayoutPassword.setError("Password should not be empty");
                } else {
                    //Here you can write the codes for checking password
                    txtInLayoutPassword.setError(null);
                }

//                if (rememberMe.isChecked()) {
//                    //Here you can write the codes if box is checked
//                } else {
//                    //Here you can write the codes if box is not checked
//                }

                if(!StringUtils.isEmpty(password.getText().toString()) && !StringUtils.isEmpty(password.getText().toString())){
                    //then login
                    goToProfile();
                }



            }

        });

    }

    private void goToProfile() {
//        Prefs prefs = new Prefs(this);
//        prefs.open();
//
//        prefs.saveLoginData("demo_token_ssdsd", reg_email.getText().toString(), username.getText().toString());
        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));

    }

    //The method for opening the registration page and another processes or checks for registering
    private void ClickSignUp() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.register, null);
        dialog.setView(dialogView);

        reg_username = dialogView.findViewById(R.id.reg_username);
        reg_password = dialogView.findViewById(R.id.reg_password);
//        reg_firstName = dialogView.findViewById(R.id.reg_firstName);
//        reg_lastName = dialogView.findViewById(R.id.reg_lastName);
        reg_email = dialogView.findViewById(R.id.reg_email);
//        reg_confirmemail = dialogView.findViewById(R.id.reg_confirmemail);
        reg_register = dialogView.findViewById(R.id.reg_register);
        txtInLayoutRegPassword = dialogView.findViewById(R.id.txtInLayoutRegPassword);

        reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reg_username.getText().toString().trim().isEmpty()) {

                    reg_username.setError("Please fill out this field");
                } else {
                    //Here you can write the codes for checking username
                    reg_username.setError(null);
                }
                if (reg_password.getText().toString().trim().isEmpty()) {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(false);
                    reg_password.setError("Please fill out this field");
                } else {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(true);
                    //Here you can write the codes for checking password
                    reg_password.setError(null);
                }
//                if (reg_firstName.getText().toString().trim().isEmpty()) {
//
//                    reg_firstName.setError("Please fill out this field");
//                } else {
//                    //Here you can write the codes for checking firstname
//
//                }
//                if (reg_lastName.getText().toString().trim().isEmpty()) {
//
//                    reg_lastName.setError("Please fill out this field");
//                } else {
//                    //Here you can write the codes for checking lastname
//                }


                if (reg_email.getText().toString().trim().isEmpty()) {

                    reg_email.setError("Please fill out this field");
                }else {
                    //Here you can write the codes for checking email
                    reg_email.setError(null);
                }
//                if (reg_confirmemail.getText().toString().trim().isEmpty()) {
//
//                    reg_confirmemail.setError("Please fill out this field");
//                } else {
//                    //Here you can write the codes for checking confirmemail
//                }

                if(!StringUtils.isEmpty(reg_email.getText().toString()) && !StringUtils.isEmpty(reg_password.getText().toString()) && !StringUtils.isEmpty(reg_username.getText().toString())){
                    //then login
                    goToProfile();
                }




            }
        });


        dialog.show();


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
