package ru.bechol.CoinCapClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bechol.CoinCapClient.models.CurrencyBookmark;
import ru.bechol.CoinCapClient.services.BookmarkService;

import java.util.List;

/**
 * Класс BookmarkController.
 * REST контроллер для работы с закладками.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@RestController
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    /**
     * Метод showAllBookmarks.
     * Возврат всех закладок.
     *
     * @return коллекция закладок.
     * @request GET http://localhost:8080/api/v1/bookmarks/all
     */
    @GetMapping("/all")
    public ResponseEntity<List<CurrencyBookmark>> showAllBookmarks() {
        List<CurrencyBookmark> bookmarkList = bookmarkService.findAllBookmarks();
        return bookmarkList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bookmarkList);
    }

    /**
     * Метод showCurrencyBookMarkById.
     * Возврат закладки по id.
     *
     * @param id Id закладки.
     * @return объект закладки.
     * @request GET http://localhost:8080/api/v1/bookmarks/one/{id}
     */
    @GetMapping("/one/{id}")
    public ResponseEntity<CurrencyBookmark> showCurrencyBookMarkById(@PathVariable Long id) {
        CurrencyBookmark currencyBookmark = bookmarkService.findBookmarkById(id);
        return currencyBookmark != null ? ResponseEntity.ok(currencyBookmark) : ResponseEntity.notFound().build();
    }

    /**
     * Метод createNewBookMark.
     * Создание закладки.
     *
     * @param newCurrencyBookmarkName наименование закладки.
     * @return 200OK - если закладка создана.
     * @request POST http://localhost:8080/api/v1/bookmarks/create
     */
    @PostMapping("/create")
    public ResponseEntity<Boolean> createNewBookMark(@RequestParam("bookmark") String newCurrencyBookmarkName) {
        if (bookmarkService.isExistByName(newCurrencyBookmarkName)) {
            return ResponseEntity.badRequest().build();
        }
        bookmarkService.addNewCurrencyBookmark(newCurrencyBookmarkName);
        return ResponseEntity.ok(true);
    }

    /**
     * Метод updateCurrencyBookmark.
     * Изменение наименования существующей закладки.
     *
     * @param id                      Id закладки.
     * @param newCurrencyBookmarkName измененное наименование.
     * @return 200OK - если наименование закладки изменено.
     * @request PUT http://localhost:8080/api/v1/bookmarks/update/{id}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateCurrencyBookmark(
            @PathVariable Long id, @RequestParam("bookmark") String newCurrencyBookmarkName) {
        return bookmarkService.updateBookMark(id, newCurrencyBookmarkName) ?
                ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    /**
     * Метод deleteCurrencyBookmark.
     * Удаление закладки.
     *
     * @param id Id закладки.
     * @return 200OK - если закладка удалена.
     * @request DELETE http://localhost:8080/api/v1/bookmarks/delete/{id}
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCurrencyBookmark(@PathVariable Long id) {
        return bookmarkService.deleteBookmarkById(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

}
