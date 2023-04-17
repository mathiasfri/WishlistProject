package com.example.wishlistproject.controller;

import com.example.wishlistproject.model.User;
import com.example.wishlistproject.repository.LoginRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/wishlist")
@Controller
public class LoginController {
    private LoginRepository loginRepository;
    private int current_userId;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    protected boolean isLoggedIn(HttpSession session, int uid) {
        return session.getAttribute("user") != null && current_userId == uid;
    }

    @GetMapping()
    public String landingPage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        // return login form
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("uid") String uid, @RequestParam("pw") String pw, HttpSession session, Model model) {
        User user = loginRepository.checkEmail(uid);
        if (user != null && user.getPassword().equals(pw)) {
            // create session for user and set session timeout to 30 sec (container default: 15 min)
            session.setAttribute("user", user);
            current_userId = user.getUserId();

            session.setMaxInactiveInterval(30);

            return "redirect:/wishlist/mainpage/" + current_userId;
        }

        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session and return landing page
        session.invalidate();
        return "login";
    }
}

