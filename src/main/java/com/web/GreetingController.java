package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login.html";
    }

    @GetMapping("/")
    public String main() {
        return "index.html";
    }

    @GetMapping("/main-screen")
    public String mainScreen() {
        return "mainScreen/mainScreen.html";
    }

    @GetMapping("/detail-product")
    public String detailProduct(@RequestParam(name="id") String id) {
        return "mainScreen/detailproduct.html";
    }
}
