package com.example.studybuddy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppControllers {

    /**
     * Volá reactapp v targetu
     * */
    @GetMapping("/{path:[^cxqp]+}/**")
    public String forward() {
        return "forward:/";
    }

}
