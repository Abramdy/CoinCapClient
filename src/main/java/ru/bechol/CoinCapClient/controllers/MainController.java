package ru.bechol.CoinCapClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    private final static String ASSETS_URL = "http://api.coincap.io/v2/assets/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<String> getAssetsView() {
        return restTemplate.getForEntity(ASSETS_URL, String.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCurrencyView(@PathVariable String id) {
        return restTemplate.getForEntity(ASSETS_URL + id, String.class);
    }
}
