package com.example.wishlistproject.controllers;

import com.example.wishlistproject.models.User;
import com.example.wishlistproject.models.Wish;
import com.example.wishlistproject.repositories.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishController {
    WishRepository repository;

    public WishController(WishRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";

    }
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        repository.createUser(user);
        return "";
    }

    @GetMapping("/create")
    public String createWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        return "";
    }
    @PostMapping("/addUser")
    public String addWish(@ModelAttribute Wish wish) {
        repository.createWish(wish);
        return "";
    }
}

