package com.example.wishlistproject.repositories;

import com.example.wishlistproject.models.User;
import com.example.wishlistproject.models.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository()
public class WishRepository {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String user_id;
    @Value("${spring.datasource.password}")
    String user_pwd;


    public void createUser(User user) {
        try(Connection con = DriverManager.getConnection(url, user_id, user_pwd)) {
            String SQL = "INSERT INTO users(first_name, last_name, user_email, user_password) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getMail());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createWish(Wish wish) {
        try(Connection con = DriverManager.getConnection(url, user_id, user_pwd)) {
            String SQL = "INSERT INTO wishlist(wish_title, wish_description, wish_url, wish_picture) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, wish.getTitle());
            pstmt.setString(2, wish.getDescription());
            pstmt.setString(3, wish.getUrl());
            pstmt.setString(4, wish.getPicture());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
