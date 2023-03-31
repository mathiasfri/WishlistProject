package com.example.wishlistproject.Repository;

import com.example.wishlistproject.Model.User;
import com.example.wishlistproject.Model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Wish> createWish(Wish wish) {
        try(Connection con = DriverManager.getConnection(url, user_id, user_pwd)) {
            String SQL = "INSERT INTO wishlist(wish_title, wish_description, wish_url, wish_picture) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, wish.getTitle());
            pstmt.setString(2, wish.getDescription());
            pstmt.setString(3, wish.getUrl());
            pstmt.setString(4, wish.getPicture());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int wishId = rs.getInt(1);
                wish.setWishId(wishId);
            }

            List<Wish> wishes = new ArrayList<>();
            String selectSQL = "SELECT * FROM wishlist";
            Statement selectStmt = con.createStatement();
            ResultSet selectRs = selectStmt.executeQuery(selectSQL);
            while (selectRs.next()) {
                Wish w = new Wish();
                w.setWishId(selectRs.getInt("wish_id"));
                w.setTitle(selectRs.getString("wish_title"));
                w.setDescription(selectRs.getString("wish_description"));
                w.setUrl(selectRs.getString("wish_url"));
                w.setPicture(selectRs.getString("wish_picture"));
                wishes.add(w);
            }
            return wishes;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
