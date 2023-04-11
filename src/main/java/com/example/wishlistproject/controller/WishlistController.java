package com.example.wishlistproject.controller;

import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.repository.WishlistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wishlist")
@Controller
public class WishlistController {
    private WishlistRepository wishlistRepository;
    public WishlistController(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }
    @GetMapping()
    public String landingPage(){
        return "index";
    }

    @GetMapping("/create")
    public String createUser(Model model){
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "create-user";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute User newUser, Model model){
        int userId = wishlistRepository.createUser(newUser);
        model.addAttribute("firstName",newUser.getFirstName());
        model.addAttribute("lastName",newUser.getLastName());
        model.addAttribute("email",newUser.getEmail());
        model.addAttribute("password",newUser.getPassword());
        model.addAttribute("userId", userId);
        return "created-user";
    }

    @GetMapping("/mainpage/{uid}")
    public String mainPage(@PathVariable int uid, Model model){
        User user = wishlistRepository.getUser(uid);

        model.addAttribute("userId", user.getUserId());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());

        List<Wish> wishList = wishlistRepository.getWishList(uid);
        model.addAttribute("wishlist", wishList);
        return "main-page";
    }

}
