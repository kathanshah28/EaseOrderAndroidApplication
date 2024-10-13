package com.example.retrofit_apigetpractice;


import retrofit2.Call;
import retrofit2.http.GET;


public interface MenuCategoryPlaceHolder {

    @GET("fetchmenucategory")
    Call<MenuCategoryResponse> getMenuCategoryList();
}
