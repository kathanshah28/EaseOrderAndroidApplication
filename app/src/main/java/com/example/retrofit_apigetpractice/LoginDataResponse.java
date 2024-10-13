package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

public class LoginDataResponse<T> {
    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private T data;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public LoginDataResponse(int statuscode, T data, String message, boolean success) {
        this.statuscode = statuscode;
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
