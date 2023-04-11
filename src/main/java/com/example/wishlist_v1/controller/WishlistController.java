package com.example.wishlist_v1.controller;

import com.example.wishlist_v1.model.User;
import com.example.wishlist_v1.repository.WishlistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishlistController {
    private WishlistRepository repository;

    public WishlistController(WishlistRepository repository) {
        this.repository = repository;
        addUser();
    }
    @GetMapping("/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";

    }
    @PostMapping("/addUser")
    public void addUser() {
        User user1 = new User();
        user1.setFirstName("Peter");
        user1.setLastName("Doe");
        user1.setMail("jd@mail.com");
        user1.setPassword("1234");
        repository.createUser(user1);
    }

}

