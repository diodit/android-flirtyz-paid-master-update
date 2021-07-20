package com.owo.phlurtyzpaid.api.interfaces;

import com.owo.phlurtyzpaid.api.models.AllCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetForAllCategories {


    @GET
    Call<List<AllCategory>> getAllData(@Url String url);
}
