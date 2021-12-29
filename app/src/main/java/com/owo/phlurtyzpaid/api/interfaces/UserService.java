package com.owo.phlurtyzpaid.api.interfaces;

import com.owo.phlurtyzpaid.api.models.UserStatus;
import com.owo.phlurtyzpaid.model.CathegoryModel;
import com.owo.phlurtyzpaid.model.LoginModel;
import com.owo.phlurtyzpaid.model.LoginResponse;
import com.owo.phlurtyzpaid.model.MobileResendCdRespose;
import com.owo.phlurtyzpaid.model.MobileResendCode;
import com.owo.phlurtyzpaid.model.RegisterModel;
import com.owo.phlurtyzpaid.model.RegisterResponse;
import com.owo.phlurtyzpaid.model.ResetPassword;
import com.owo.phlurtyzpaid.model.ResetPasswordRespond;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @PUT("membership/api/mobile/resend/verification")
    Call<MobileResendCdRespose> getResentcode(@Body MobileResendCode getMobileCode);

    @POST("membership/api/auth/register") 
    Call<RegisterResponse>getRegisterResponse(@Body RegisterModel getRegisterModel);

    @POST("membership/api/auth/login")
    Call<LoginResponse>getLoginResponse(@Body LoginModel loginModel);

    @POST("membership/api/password/mobile/reset")
    Call<ResetPasswordRespond>getResetPasswordResponse(@Body ResetPassword resetPassword);

    @GET("api/category/getInAppCategories")
    Call<List<CathegoryModel>> getCathegorieModel();

    @GET("api/emojiGroup/getAllWIthStatus?status=ACCEPTED")
    Call<List<UserStatus>> getUserStatus (@Header("Authorization")String token);



}
