package com.example.retrofit_apigetpractice;

import androidx.annotation.StringRes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderHistoryDataModel {

    @SerializedName("_id")
    private String id;

    @SerializedName("fname")
    private String fName;

    @SerializedName("lname")
    private String lName;

    @SerializedName("email")
    private String email;

    @SerializedName("restaurant_Name")
    private String restaurant_Name;

    @SerializedName("tableNo")
    private int tableNo;

    @SerializedName("items")
    private List<OrderItemsModel> OrderedItem;

    @SerializedName("amount")
    private double amount;

    @SerializedName("payment")
    private String payment;

    @SerializedName("status")
    private String status;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    public OrderHistoryDataModel(String id, String fName, String lName, String email, String restaurant_Name, int tableNo, List<OrderItemsModel> orderedItem, double amount, String payment, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.restaurant_Name = restaurant_Name;
        this.tableNo = tableNo;
        OrderedItem = orderedItem;
        this.amount = amount;
        this.payment = payment;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurant_Name() {
        return restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        this.restaurant_Name = restaurant_Name;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public List<OrderItemsModel> getOrderedItem() {
        return OrderedItem;
    }

    public void setOrderedItem(List<OrderItemsModel> orderedItem) {
        OrderedItem = orderedItem;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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



