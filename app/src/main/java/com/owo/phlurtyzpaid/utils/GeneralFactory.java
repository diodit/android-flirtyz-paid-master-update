package com.owo.phlurtyzpaid.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.owo.phlurtyzpaid.activity.MyLogin;
import com.owo.phlurtyzpaid.api.models.UserStatus;
import com.owo.phlurtyzpaid.service.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralFactory {
    private Context context;
    private static GeneralFactory generalFactory;
    private List<UserStatus> userStatuses;

    private GeneralFactory(Context context){
        this.context = context;
        userStatuses = new ArrayList<>();

    }

    public static GeneralFactory getGeneralFactory(Context context) {
        if (generalFactory == null) {
            generalFactory = new GeneralFactory(context);
        }
        return generalFactory;
    }

    public void loadFromApiUserStatus(String token, FetchUserStatus userStatus){

        Call<List<UserStatus>> userStatusCall = ApiClient.getService().getUserStatus("Bearer "+token);
        userStatusCall.enqueue(new Callback<List<UserStatus>>() {
            @Override
            public void onResponse(Call<List<UserStatus>> call, Response<List<UserStatus>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MyLogin.class);
                    context.startActivity(intent);

                    return;
                }

                userStatuses = response.body();

                userStatus.userFetcher(userStatuses);

                Log.d("status", ""+userStatuses.size());

            }

            @Override
            public void onFailure(Call<List<UserStatus>> call, Throwable t) {
                Log.d("cathenotshown", ""+t.getMessage());

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public interface FetchUserStatus {
        void userFetcher(List<UserStatus> friends);
    }


}
