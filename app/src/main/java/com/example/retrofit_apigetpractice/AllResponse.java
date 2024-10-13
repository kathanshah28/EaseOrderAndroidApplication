package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllResponse<T> {

    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private List<T> orderData;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public List<T> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<T> orderData) {
        this.orderData = orderData;
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
