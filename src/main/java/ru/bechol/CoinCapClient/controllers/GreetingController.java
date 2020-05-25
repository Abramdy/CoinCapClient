package ru.bechol.CoinCapClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping
    public String getGreeting() {
        return "index";
    }
}
