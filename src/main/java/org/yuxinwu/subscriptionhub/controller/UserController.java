package org.yuxinwu.subscriptionhub.controller;

import org.yuxinwu.subscriptionhub.model.User;
import org.yuxinwu.subscriptionhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        // Create a new user object to bind form data
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        // Register a new user
        userService.registerUser(user);
        return "redirect:/login";  // Redirect to login after registration
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model, @RequestParam String email) {
        // Display user profile by email
        User user = userService.findUserByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found");
        }
        return "profile";
    }
}

