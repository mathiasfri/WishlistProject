package com.example.wishlistproject.controller;

import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.repository.WishlistRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("wishlist")
@Controller
public class WishlistController {
    private WishlistRepository wishlistRepository;
    private LoginController loginController;

    public WishlistController(WishlistRepository wishlistRepository, LoginController loginController){
        this.wishlistRepository = wishlistRepository;
        this.loginController = loginController;
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
    public String mainPage(@PathVariable int uid, Model model, HttpSession session){
        User user = wishlistRepository.getUser(uid);

        model.addAttribute("userId", user.getUserId());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());

        List<Wish> wishList = wishlistRepository.getWishList((uid));
        model.addAttribute("wishlist", wishList);

        return loginController.isLoggedIn(session, uid) ? "main-page" : "login";
    }

    @GetMapping("/createwish/{uid}")
    public String createWish(@PathVariable int uid, Model model){
        Wish newWish = new Wish();
        newWish.setUserId(uid);
        model.addAttribute("newWish", newWish);
        return "create-wish";
    }

    @PostMapping("/addwish")
    public String addWish(@ModelAttribute Wish newWish){
        wishlistRepository.createWish(newWish);
        return "redirect:/wishlist/mainpage/" + newWish.getUserId();
    }

    @GetMapping("/updatewish/{wid}")
    public String updateWish(@PathVariable int wid, Model model){
        Wish updateWish = wishlistRepository.getSpecificWish(wid);
        model.addAttribute("updateWish", updateWish);
        return"update-wish";
    }

    @PostMapping("/updatewish")
    public String updateUserWish(@ModelAttribute Wish wishUpdate){
        wishlistRepository.updateWish(wishUpdate);
        return "redirect:/wishlist/mainpage/" + wishUpdate.getUserId();
    }

    @GetMapping("/deletewish/{uid}/{wid}")
    public String deleteWish(@PathVariable int uid, @PathVariable int wid){
        wishlistRepository.deleteWish(wid);
        return "redirect:/wishlist/mainpage/" + uid;
    }
}
