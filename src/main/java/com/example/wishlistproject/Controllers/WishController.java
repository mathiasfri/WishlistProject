package com.example.wishlistproject.Controllers;

import com.example.wishlistproject.Models.User;
import com.example.wishlistproject.Repositories.WishRepository;
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

}

