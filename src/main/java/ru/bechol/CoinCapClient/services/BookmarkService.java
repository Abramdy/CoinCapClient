package ru.bechol.CoinCapClient.services;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bechol.CoinCapClient.exceptions.CurrencyBookmarkNotFound;
import ru.bechol.CoinCapClient.models.CurrencyBookmark;
import ru.bechol.CoinCapClient.repositories.BookmarkRepository;

import java.util.List;

/**
 * Класс BookmarkService.
 * Сервисный слой для закладок.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Slf4j
@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    /**
     * Метод findAllBookmarks.
     * Вывод всех существующих в базе закладок.
     *
     * @return коллекция закладок.
     */
    public List<CurrencyBookmark> findAllBookmarks() {
        return bookmarkRepository.findAll();
    }

    /**
     * Метод findBookmarkById.
     * Поиск закладки по Id.
     *
     * @param id Id закладки.
     * @return объект закладки.
     */
    public CurrencyBookmark findBookmarkById(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new CurrencyBookmarkNotFound("Currency bookmark not found by Id."));
    }

    /**
     * Метод findCurrencyBookmarkByName.
     * Поиск закладки по имени.
     *
     * @param cyrrencyBookmarkName имя закладки.
     * @return имя закладки.
     */
    public String findCurrencyBookmarkByName(String cyrrencyBookmarkName) {
        return bookmarkRepository.findByCurrencyName(cyrrencyBookmarkName.trim())
                .orElseThrow(() -> new CurrencyBookmarkNotFound("Currency bookmark not found by name."));
    }

    /**
     * Метод addNewCurrencyBookmark.
     * Создание новой закладки.
     *
     * @param newCurrencyBookmarkName название новой закладки (по имени валюты).
     * @return true - если закладка сохранена.
     */
    public boolean addNewCurrencyBookmark(String newCurrencyBookmarkName) {
        if (!Strings.isNullOrEmpty(newCurrencyBookmarkName)) {
            bookmarkRepository.save(new CurrencyBookmark(newCurrencyBookmarkName.trim()));
            log.info("New bookmark {} saved.", newCurrencyBookmarkName);
            return true;
        }
        log.warn("CurrencyBookmark saving failed.");
        return false;
    }

    /**
     * Метод updateBookMark.
     * Обновление закладки.
     *
     * @param id              Id закладки.
     * @param newBookMarkName новое наименование закладки.
     * @return true - если закладка обновлена.
     */
    public boolean updateBookMark(Long id, String newBookMarkName) {
        CurrencyBookmark currencyBookmark = findBookmarkById(id);
        if (currencyBookmark == null) {
            log.warn("Currency currencyBookmark not found by Id.");
            return false;
        }
        currencyBookmark.setCurrencyName(newBookMarkName);
        bookmarkRepository.save(currencyBookmark);
        log.info("CurrencyBookmark {} updated.", currencyBookmark.getCurrencyName());
        return true;
    }

    /**
     * Метод deleteBookmarkById.
     * Удаление закладки по Id.
     *
     * @param id Id закладки.
     * @return true - если закладка удалена.
     */
    public boolean deleteBookmarkById(Long id) {
        if (bookmarkRepository.findById(id).isPresent()) {
            bookmarkRepository.deleteById(id);
            log.info("CurrencyBookmark deleted.");
            return true;
        }
        log.warn("Currency bookmark not found by Id.");
        return false;
    }

    /**
     * Метод isExistByName.
     * Проверка существования закладки по имени.
     * @param bookmarkName имя закладки.
     * @return true - если закладка существуеты
     */
    public boolean isExistByName(String bookmarkName) {
        return bookmarkRepository.findByCurrencyName(bookmarkName).isPresent();
    }
}
