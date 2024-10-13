package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderHistoryResponse {

    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private List<OrderHistoryDataModel> orderData;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public OrderHistoryResponse(int statuscode, List<OrderHistoryDataModel> orderData, String message, boolean success) {
        this.statuscode = statuscode;
        this.orderData = orderData;
        this.message = message;
        this.success = success;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public List<OrderHistoryDataModel> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<OrderHistoryDataModel> orderData) {
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
