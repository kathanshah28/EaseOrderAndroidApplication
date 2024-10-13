package com.example.retrofit_apigetpractice;

import com.google.gson.annotations.SerializedName;

public class MenuCategory {

    @SerializedName("_id")
    private String id;
    @SerializedName("menu_name")
    private String name;
    @SerializedName("menu_image")
    private String imageurl;

    public MenuCategory(String id, String name, String imageurl) {
        this.id = id;
        this.name = name;
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
