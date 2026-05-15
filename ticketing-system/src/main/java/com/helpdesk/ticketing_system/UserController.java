package com.helpdesk.ticketing_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String role, Model model) {
        try {
            userService.registerUser(email, password, role);
            model.addAttribute("message", "Registration successful!");
            return "login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.login(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        // For simplicity, we just redirect to a dashboard page
        model.addAttribute("user", user); 
        return "dashboard";
    }
}
