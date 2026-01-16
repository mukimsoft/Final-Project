package com.example.finalproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        // This redirects the root URL to the /tasks endpoint handled by TaskController
        return "redirect:/tasks";
    }
}
