package com.example.wishlistproject.Model;

import java.util.ArrayList;
import java.util.List;

public class Wish {
    private int wishId;
    private String title;
    private String picture;
    private String description;
    private String url;
    private int userId;
    private List<Wish> wishes = new ArrayList<>();

    public Wish(){}
    public Wish(int wishId, String title, String picture, String description, String url, int userId) {
        this.wishId = wishId;
        this.title = title;
        this.picture = picture;
        this.description = description;
        this.url = url;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserID() {
        return userId;
    }

    public void setUserID(int userID) {
        this.userId = userID;
    }
}

