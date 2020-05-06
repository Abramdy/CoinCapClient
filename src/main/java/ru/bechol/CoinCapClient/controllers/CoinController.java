package ru.bechol.CoinCapClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.bechol.CoinCapClient.services.BookmarkService;

/**
 * Класс CoinController.
 * REST контроллер для работы с криптовалютами.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@RestController
@RequestMapping("/api/v1/coins")
public class CoinController {

    private final static String ASSETS_URL = "http://api.coincap.io/v2/assets/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookmarkService bookmarkService;

    /**
     * Метод getAssetsView.
     * Возврат всех валют.
     *
     * @return список криптовалют.
     * @request GET http://localhost:8080/api/v1/coins/all
     */
    @GetMapping("/all")
    public ResponseEntity<String> getAssetsView() {
        return restTemplate.getForEntity(ASSETS_URL, String.class);
    }

    /**
     * Метод getCurrencyView.
     * Возврат криптовалюты по id.
     *
     * @return объект криптовалюты.
     * @request GET http://localhost:8080/api/v1/coins/one/{id}
     */
    @GetMapping("/one/{id}")
    public ResponseEntity<String> getCurrencyView(@PathVariable String id) {
        return restTemplate.getForEntity(ASSETS_URL + id, String.class);
    }

    /**
     * Метод getCurrencyViewByBookmark.
     * Возврат криптовалюты по закладке.
     * @param bookmarkName имя закладки.
     * @return объект криптовалюты.
     */
    @GetMapping("/bookmark/{bookmarkName}")
    public ResponseEntity<String> getCurrencyViewByBookmark(@PathVariable String bookmarkName) {
        if (!bookmarkService.isExistByName(bookmarkName)) {
            return ResponseEntity.notFound().build();
        }
        return getCurrencyView(bookmarkName);
    }
}
