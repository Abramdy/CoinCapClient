package ru.bechol.CoinCapClient.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @GetMapping
    public String getGreetingView() {
        return "index";
    }
}
