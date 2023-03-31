package com.example.wishlistproject.Controller;

import com.example.wishlistproject.Model.User;
import com.example.wishlistproject.Model.Wish;
import com.example.wishlistproject.Repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishController {
    WishRepository repository;

    public WishController(WishRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/createuser")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "cuser";

    }
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute User user) {
        repository.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/createwish")
    public String createWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        return "cwish";
    }

    @PostMapping("/addwish")
    public String addWish(@ModelAttribute Wish wish) {
        repository.createWish(wish);
        return "redirect:/createwish";
    }

    @PostMapping("/createwish")
    public String createWish(@ModelAttribute Wish wish, Model model) {
        repository.createWish(wish);
        List<Wish> wishes = repository.createWish(wish);
        model.addAttribute("wishes", wishes);
        return "wishlist";
    }


}

