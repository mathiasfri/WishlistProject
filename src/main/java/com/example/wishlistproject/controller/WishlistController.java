package com.example.wishlistproject.controller;

import com.example.wishlistproject.repository.WishlistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
