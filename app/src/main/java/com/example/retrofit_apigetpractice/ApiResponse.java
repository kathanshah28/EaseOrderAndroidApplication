package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse<T>{
    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private List<T> data;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public ApiResponse(int statuscode, List<T> data, String message, boolean success) {
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
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
