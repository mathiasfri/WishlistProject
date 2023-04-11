package com.example.wishlistproject.dto;

import com.example.wishlistproject.model.Wish;

import java.util.List;

public class UserWishlistDTO {
    private int userId;
    private String firstName;
    private String lastName;
    List<Wish> userWishlist;

    public UserWishlistDTO() {}

    public UserWishlistDTO(int userId, String firstName, String lastName, List<Wish> userWishlist) {
        this.userId =userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userWishlist = userWishlist;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Wish> getUserWishlist() {
        return userWishlist;
    }

    public void addWish(Wish wish){
        userWishlist.add(wish);
    }
}
