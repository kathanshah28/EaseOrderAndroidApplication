package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

public class OrderItemsModel {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String foodName;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private int price;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("category")
    private String category;

    @SerializedName("__v")
    private String version;

    //can be cause error because don't know which datatype this quantity belongs to
    @SerializedName("quantity")
    private String quantity;

    @SerializedName("status")
    private String status;

    public OrderItemsModel(String id, String foodName, String description, int price, String imageUrl, String category, String version, String quantity, String status) {
        this.id = id;
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.version = version;
        this.quantity = quantity;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
