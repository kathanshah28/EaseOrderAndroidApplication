package com.example.retrofit_apigetpractice;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CartResponseJsonPlaceHolder {

    @POST("getcartitems")
    Call<CartItemsReponse> getCartData(@Header("token") String token);
}
