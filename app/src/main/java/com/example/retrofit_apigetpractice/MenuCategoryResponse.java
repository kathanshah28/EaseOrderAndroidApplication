package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuCategoryResponse {

    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private List<MenuCategory> menuCategorydata;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public MenuCategoryResponse(int statuscode, List<MenuCategory> menuCategorydata, String message, boolean success) {
        this.statuscode = statuscode;
        this.menuCategorydata = menuCategorydata;
        this.message = message;
        this.success = success;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public List<MenuCategory> getMenuCategorydata() {
        return menuCategorydata;
    }

    public void setFooddata(List<MenuCategory> menuCategorydata) {
        this.menuCategorydata = menuCategorydata;
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
