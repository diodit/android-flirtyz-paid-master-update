package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.owo.phlurtyzpaid.ForgetPassword;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.model.LoginModel;
import com.owo.phlurtyzpaid.model.LoginResponse;
import com.owo.phlurtyzpaid.service.ApiClient;
import com.owo.phlurtyzpaid.service.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyLogin extends AppCompatActivity {
    private TextView backLogin, register, forget;
    private MaterialButton myLogin;
    private TextInputEditText emaill, password;
    private String user_email, user_password, user_token;
    private String email, passwo;
    LoginModel loginModel;
    private ProgressBar progressBar;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        backLogin = findViewById(R.id.back);
        register = findViewById(R.id.register);
        myLogin = findViewById(R.id.login);
        forget = findViewById(R.id.forgetPassword);
        progressBar = findViewById(R.id.progress_bar);
        linear = findViewById(R.id.linearLayer);


        emaill = findViewById(R.id.textField);
        password = findViewById(R.id.passwordd);

        if (checkUserlogged()){
            progressBar.setVisibility(View.VISIBLE);
            linear.setVisibility(View.GONE);
            loginModel = new LoginModel("", "");
            email = SharedPref.getInstance(MyLogin.this).geStoredEmail();
            passwo = SharedPref.getInstance(MyLogin.this).getStoredPassword();
            loginModel.setEmail(email);
            loginModel.setPassword(passwo);

            login(loginModel);

        } else {
            progressBar.setVisibility(View.GONE);
            linear.setVisibility(View.VISIBLE);
        }


        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLogin.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLogin.this, GetStarted.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLogin.this, Register.class);
                startActivity(intent);
            }
        });


        myLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_email = emaill.getText().toString();
                user_password = password.getText().toString();


                if (user_email.isEmpty()){
                    emaill.setError("Email is required");
                    emaill.requestFocus();

                }else if (user_password.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                }else {
                    loginModel = new LoginModel("", "");

                    loginModel.setEmail(user_email);
                    loginModel.setPassword(user_password);
                    login(loginModel);
                }

            }
        });
    }


    public void login(LoginModel loginModel){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().getLoginResponse(loginModel);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){

                    Intent intent = new Intent(MyLogin.this, WelcomeScreen.class);

                    if (!checkUserlogged()){
                        SharedPref.getInstance(MyLogin.this).setStoredEmail("email", user_email );
                        SharedPref.getInstance(MyLogin.this).setStoredPassword("password", user_password );

                    }

                    LoginResponse loginResponse = response.body();
                    loginResponse.getStatus();
                    user_token = loginResponse.getAccess_token();

                    SharedPref.getInstance(MyLogin.this).setStoredToken("token", user_token);

                    Toast.makeText(MyLogin.this, loginResponse.getStatus(), Toast.LENGTH_LONG).show();

                    Log.d("logged in", ""+user_token);
                    startActivity(intent);

                }else{
                    Toast.makeText(MyLogin.this, response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(MyLogin.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }



    private boolean checkUserlogged(){
        if ((SharedPref.getInstance(MyLogin.this).geStoredEmail() != null) && (SharedPref.getInstance(MyLogin.this).getStoredPassword() != null)){
            return true;
        }

        return false;
    }

}