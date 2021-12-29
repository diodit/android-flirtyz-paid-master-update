package com.owo.phlurtyzpaid.service;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";
    private static SharedPref pref;
    private final SharedPreferences sharedPref;

    private SharedPref(Context context) {
        sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

    }

    public static SharedPref getInstance(Context context) {
        if (pref == null) pref = new SharedPref(context);
        return pref;
    }

    public String geStoredEmail() {
        return sharedPref.getString(EMAIL, null);
    }


    public void setStoredEmail(String key, String email) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, email);
        editor.apply();

    }

    public String getStoredToken(){
        return  sharedPref.getString(TOKEN, null);
    }

    public void setStoredToken(String key, String token){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, token);
        editor.apply();
    }

    public String getStoredPassword() {
        return sharedPref.getString(PASSWORD, null);
    }


    public void setStoredPassword(String key, String password) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, password);
        editor.apply();

    }

    public void deleteAllDetails(String email, String password){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(email);
        editor.remove(password);
        editor.apply();
    }

}
