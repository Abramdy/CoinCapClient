package ru.bechol.CoinCapClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    @GetMapping("/hello")
    public String getGreeting() {
        return "index";
    }
}
