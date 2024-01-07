package com.example.socialnisitprostudenty.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppControllers {

    @GetMapping("/{path:[^\\.]+}/**")
    public String forward() {
        return "login";
    }
}
