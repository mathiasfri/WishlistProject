package com.example.wishlistproject.Controllers;

import com.example.wishlistproject.Repositories.WishRepository;
import org.springframework.stereotype.Controller;

@Controller
public class WishController {
    WishRepository repository = new WishRepository();

    public WishController(WishRepository repository) {
        this.repository = repository;
    }
}

