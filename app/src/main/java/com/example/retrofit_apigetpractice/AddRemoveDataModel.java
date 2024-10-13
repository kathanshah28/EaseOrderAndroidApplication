package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class AddRemoveDataModel {
    @SerializedName("_id")
    private String id;
    @SerializedName("restaurant_Name")
    private String restaurant_Name;
    @SerializedName("tableNo")
    private String tableNo;
    @SerializedName("cartData")
    private Map<String, Integer> cartData;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;

    public AddRemoveDataModel(String id, String restaurant_Name, String tableNo, Map<String, Integer> cartData, String createdAt, String updatedAt) {
        this.id = id;
        this.restaurant_Name = restaurant_Name;
        this.tableNo = tableNo;
        this.cartData = cartData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurant_Name() {
        return restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        this.restaurant_Name = restaurant_Name;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public Map<String, Integer> getCartData() {
        return cartData;
    }

    public void setCartData(Map<String, Integer> cartData) {
        this.cartData = cartData;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
