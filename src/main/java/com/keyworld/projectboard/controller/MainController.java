package com.keyworld.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String getHomePage() {
        return "Welcome to the home page!";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "Welcome to the admin page!";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "Please login to continue.";
    }
}
