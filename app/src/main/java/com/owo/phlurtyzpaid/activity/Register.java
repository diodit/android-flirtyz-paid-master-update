package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.model.RegisterModel;
import com.owo.phlurtyzpaid.model.RegisterResponse;
import com.owo.phlurtyzpaid.service.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private TextView back, login;
    private MaterialButton registerScreen;
    private TextInputEditText first_name, last_name, email_register, password_register, confrim_password;
    private String user_name, user_lastName, user_email, user_password, user_confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        back = findViewById(R.id.backRegister);
        login = findViewById(R.id.login);
        registerScreen = findViewById(R.id.register);
        first_name = findViewById(R.id.textField);
        last_name = findViewById(R.id.name_last);
        email_register = findViewById(R.id.emailUser);
        password_register = findViewById(R.id.password_register);
        confrim_password = findViewById(R.id.confirm_pass);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, GetStarted.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Register.this, CheckoutActivity.class);
                startActivity(intent);
            }
        });

        registerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_name = first_name.getText().toString();
                user_lastName = last_name.getText().toString();
                user_email = email_register.getText().toString();
                user_password = password_register.getText().toString();
                user_confirmPassword = confrim_password.getText().toString();


                if (user_name.isEmpty()) {
                    first_name.setError("First Name is required");
                    first_name.requestFocus();

                }else if (user_lastName.isEmpty()) {
                    last_name.setError("Last Name is required");
                    last_name.requestFocus();

                }else if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
                    email_register.setError("Enter a Valid email");
                    email_register.requestFocus();

                }else if(!user_password.equals(user_confirmPassword)) {
                    password_register.setError("password does not match");
                    password_register.requestFocus();
                    confrim_password.requestFocus();

                }else if (user_password.length() < 6){
                    password_register.setError("Password should be at aleast 6 character long");
                    password_register.requestFocus();

                }else if (TextUtils.isEmpty(first_name.getText().toString()) || TextUtils.isEmpty(last_name.getText().toString())
                        || TextUtils.isEmpty(email_register.getText().toString())
                        || TextUtils.isEmpty(password_register.getText().toString()) || TextUtils.isEmpty(confrim_password.getText().toString())){
                    String message = "All inputs required";
                    Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();

                }else {
                    RegisterModel registerModel = new RegisterModel("","", "",
                            "", "");

                    registerModel.setFirst_name(user_name);
                    registerModel.setLast_name(user_lastName);
                    registerModel.setEmail(user_email);
                    registerModel.setPassword(user_password);
                    registerModel.setPassword_confirmation(user_confirmPassword);

                    registerUser(registerModel);

                }

            }
        });
    }

    public  void registerUser(RegisterModel registerModel1){
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().getRegisterResponse(registerModel1);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){

                    Intent intent = new Intent(Register.this, WelcomeScreen.class);

                    RegisterResponse registerResponse = response.body();
                    registerResponse.getStatus();
                    registerResponse.getMessage();

                    Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("getalldetails", ""+response.body().toString());
                    startActivity(intent);



                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_LONG).show();

                Log.d("Error", "Something went wrong" + t.getMessage());


            }
        });
    }
}