package com.example.retrofit_apigetpractice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderDataJsonPlaceHolder {

    @POST("getordersbytable")
    Call<OrderHistoryResponse> getOrderHistoryByTableNo(@Header("token") String token);
}
