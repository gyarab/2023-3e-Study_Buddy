package com.example.studybuddy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppControllers {

    @GetMapping("/{path:[^cxqp]+}/**")
    public String forward() {
        return "forward:/";
    }

}
