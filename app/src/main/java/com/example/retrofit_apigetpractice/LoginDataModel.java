package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

public class LoginDataModel {
    @SerializedName("restaurant_Name")
    String restaurant_Name;
    @SerializedName("tableNo")
    int table_No;

    public LoginDataModel(String restaurant_Name, int table_No) {
        this.restaurant_Name = restaurant_Name;
        this.table_No = table_No;
    }

    public String getRestaurant_Name() {
        return restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        this.restaurant_Name = restaurant_Name;
    }

    public int getTable_No() {
        return table_No;
    }

    public void setTable_No(int table_No) {
        this.table_No = table_No;
    }

}
