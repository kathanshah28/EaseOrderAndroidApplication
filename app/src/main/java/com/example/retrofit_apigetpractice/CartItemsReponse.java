package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class CartItemsReponse {
    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private Map<String, Integer> cartData;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public CartItemsReponse(int statuscode, Map<String, Integer> cartData, String message, boolean success) {
        this.statuscode = statuscode;
        this.cartData = cartData;
        this.message = message;
        this.success = success;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public Map<String, Integer> getCartData() {
        return cartData;
    }

    public void setCartData(Map<String, Integer> cartData) {
        this.cartData = cartData;
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
