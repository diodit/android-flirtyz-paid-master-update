package com.owo.phlurtyzpaid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.lang3.StringUtils;

public class Prefs {

    private static final String PREFS_SETTINGS = "SETTINGS";
    private static final String FIRST_TIME = "firstTime";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String AUTH_TOKEN = "authToken";
    private static final String USERNAME = "username";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences(PREFS_SETTINGS, Context.MODE_PRIVATE);
    }

    public Prefs(Context context, String name) {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public Prefs open() {
        editor = preferences.edit();
        return this;
    }

    public Prefs putInt(String key, int value) {
        editor.putInt(key, value);
        return this;
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public Prefs putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return this;
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public Prefs putString(String key, String value) {
        editor.putString(key, value);
        return this;
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void close() {
        editor.apply();
    }

    public Boolean isFirstTime() {

        if (preferences.getBoolean(FIRST_TIME, true)) {

            editor.putBoolean(FIRST_TIME, false);

            editor.commit();

            return true;

        } else {

            return false;
        }
    }


    public Boolean isLoggedIn() {

        if (StringUtils.isEmpty(preferences.getString(AUTH_TOKEN, ""))) {

//            editor.putString(AUTH_TOKEN, "");
//
//            editor.commit();

            return false;

        } else {

            return true;
        }
    }

    public void logout() {
        putString(AUTH_TOKEN, "");
        putString(EMAIL_ADDRESS, "");
        putString(USERNAME, "");
    }

    public void saveLoginData(String token, String email, String username) {
        putString(AUTH_TOKEN, token);
        putString(EMAIL_ADDRESS, email);
        putString(USERNAME, username);
    }
}
