package com.example.wishlistproject.repository;

import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String user_id;
    @Value("${spring.datasource.password}")
    String user_pwd;

    public int createUser(User newUser) {
        int userId = 0;
        try (Connection con = DriverManager.getConnection(url, user_id, user_pwd)) {
            String SQL = "INSERT INTO users (first_name, last_name, user_email, user_password) values (?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newUser.getFirstName());
            pstmt.setString(2, newUser.getLastName());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPassword());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                userId = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userId;
    }

    public User getUser(int uid){
        User user = new User();
        try (Connection con = DriverManager.getConnection(url, user_id, user_pwd)) {
            String SQL = "SELECT * FROM users WHERE user_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getString("user_password"));
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void createWish(Wish newWish){
        try(Connection con = DriverManager.getConnection(url,user_id,user_pwd)) {
            String SQL = "INSERT INTO wishlist(wish_title, wish_description, wish_url, user_id) VALUES(?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1,newWish.getTitle());
            pstmt.setString(2,newWish.getDescription());
            pstmt.setString(3,newWish.getUrl());;
            pstmt.setInt(4,newWish.getUserId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Wish> getWishList(int usersId){
        List<Wish> wishList = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(url,user_id,user_pwd)) {
            String SQL = "SELECT * FROM wishlist WHERE user_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, usersId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int wishId = rs.getInt("wish_id");
                String title = rs.getString("wish_title");
                String description = rs.getString("wish_description");
                String url = rs.getString("wish_url");
                int id = rs.getInt("user_id");

                wishList.add(new Wish(wishId,title,description,url,id));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wishList;
    }

    public Wish getSpecificWish(int wishId){
        Wish wishFound = null;
        try(Connection con = DriverManager.getConnection(url,user_id,user_pwd)){
            String SQL = "SELECT * FROM wishlist WHERE wish_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1,wishId);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                wishFound = new Wish(
                        rs.getInt("wish_id"),
                        rs.getString("wish_title"),
                        rs.getString("wish_description"),
                        rs.getString("wish_url"),
                        rs.getInt("user_id"));

            }
            return wishFound;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateWish(Wish wish){
        try(Connection con = DriverManager.getConnection(url,user_id,user_pwd)) {
            String SQL = "UPDATE wishlist SET wish_title=?, wish_description=?, wish_url=? WHERE wish_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1,wish.getTitle());
            pstmt.setString(2,wish.getDescription());
            pstmt.setString(3,wish.getUrl());
            pstmt.setInt(4,wish.getWishId());

            pstmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteWish(int wishId){
        try(Connection con = DriverManager.getConnection(url,user_id,user_pwd)) {
            String SQL = "DELETE FROM wishlist WHERE wish_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, wishId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
