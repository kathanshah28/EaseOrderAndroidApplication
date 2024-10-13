package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {
    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private List<Food> fooddata;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public FoodResponse(int statuscode, List<Food> fooddata, String message, boolean success) {
        this.statuscode = statuscode;
        this.fooddata = fooddata;
        this.message = message;
        this.success = success;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public List<Food> getFooddata() {
        return fooddata;
    }

    public void setFooddata(List<Food> fooddata) {
        this.fooddata = fooddata;
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
