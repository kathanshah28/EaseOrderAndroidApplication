package com.example.retrofit_apigetpractice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginDataPostJsonPlaceHolder {
    @POST ("login")
    Call<LoginDataResponse<LoginTokenModel>> loginTable(@Body LoginDataModel loginDataModel);
}
