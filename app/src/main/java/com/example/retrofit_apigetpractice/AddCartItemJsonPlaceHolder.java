package com.example.retrofit_apigetpractice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AddCartItemJsonPlaceHolder {

    @POST("addtocart")
    Call<AddRemoveResponseModel> addCartItem(@Header("token") String token, @Body AddRemoveBodyModel addBodyModel);

    @POST("removefromcart")
    Call<AddRemoveResponseModel> removeCartItem(@Header("token") String token, @Body AddRemoveBodyModel addBodyModel);
}
