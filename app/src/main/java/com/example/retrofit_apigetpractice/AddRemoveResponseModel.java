package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddRemoveResponseModel<T> {
    @SerializedName("statuscode")
    private int statuscode;
    @SerializedName("data")
    private AddRemoveDataModel addRemoveDataModel;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;

    public AddRemoveResponseModel(int statuscode, AddRemoveDataModel addRemoveDataModel, String message, boolean success) {
        this.statuscode = statuscode;
        this.addRemoveDataModel = addRemoveDataModel;
        this.message = message;
        this.success = success;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public AddRemoveDataModel addRemoveDataModel() {
        return addRemoveDataModel;
    }

    public void addRemoveDataModel(AddRemoveDataModel addRemoveDataModel) {
        this.addRemoveDataModel = addRemoveDataModel;
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
