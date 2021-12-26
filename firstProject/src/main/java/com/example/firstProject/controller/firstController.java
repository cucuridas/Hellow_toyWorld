package com.example.firstProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class firstController {
    @GetMapping("/")
    public String firstCont(Model model) {
        model.addAttribute("username","쿠쿠리다스");
        return "greeting";
    }
}
