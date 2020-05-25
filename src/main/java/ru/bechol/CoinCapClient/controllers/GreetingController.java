package ru.bechol.CoinCapClient.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    public String getGreetingView() {
        return "index";
    }
}
