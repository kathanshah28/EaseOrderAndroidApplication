package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

public class LoginTokenModel {
    @SerializedName("token")
    String loginToken;

    public LoginTokenModel(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}
