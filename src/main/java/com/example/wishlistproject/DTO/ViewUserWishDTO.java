package com.example.wishlistproject.DTO;

import com.example.wishlistproject.Model.Wish;

import java.util.List;

public class ViewUserWishDTO {
    private int userID;
    private String firstName;
    private String lastName;
    private List<Wish> wishList;

    public ViewUserWishDTO(int userID, String firstName, String lastName, List<Wish> wishList) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.wishList = wishList;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Wish> getWishList() {
        return wishList;
    }

    public void setWishList(List<Wish> wishList) {
        this.wishList = wishList;
    }

}
