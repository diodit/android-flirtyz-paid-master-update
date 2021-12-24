package com.owo.phlurtyzpaid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.model.ResetPassword;
import com.owo.phlurtyzpaid.model.ResetPasswordRespond;
import com.owo.phlurtyzpaid.service.ApiClient;
import com.owo.phlurtyzpaid.service.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {
    private TextInputEditText input_email;
    private MaterialButton reset_button;
    private TextView back;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);

        input_email = findViewById(R.id.emial_field);
        reset_button = findViewById(R.id.resetPassword);
        back = findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassword.this, MyLogin.class);
                startActivity(intent);
            }
        });

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = input_email.getText().toString();

                if (email.isEmpty()) {
                    input_email.setError("Email is required");
                    input_email.requestFocus();
                }else {
                    ResetPassword resetPassword = new ResetPassword("");

                    resetPassword.setEmail(email);
                    resetPassword(resetPassword);
                }

            }
        });

    }

    private void resetPassword(ResetPassword resetPassword){
        Call<ResetPasswordRespond> resetPasswordRespondCall = ApiClient.getService().getResetPasswordResponse(resetPassword);
        resetPasswordRespondCall.enqueue(new Callback<ResetPasswordRespond>() {
            @Override
            public void onResponse(Call<ResetPasswordRespond> call, Response<ResetPasswordRespond> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(ForgetPassword.this, GetStarted.class);

                    SharedPref.getInstance(ForgetPassword.this).deleteAllDetails("email", "password");
                    ResetPasswordRespond resetPasswordRespond = response.body();
                    resetPasswordRespond.getMessage();
                    Toast.makeText(ForgetPassword.this, resetPasswordRespond.getMessage(), Toast.LENGTH_LONG).show();

                    Log.d("logged in", ""+resetPasswordRespond.getMessage());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordRespond> call, Throwable t) {
                Toast.makeText(ForgetPassword.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}