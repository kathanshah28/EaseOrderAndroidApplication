package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

public class AddRemoveBodyModel {
    @SerializedName("id")
    private String foodId;

    public AddRemoveBodyModel(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
}
