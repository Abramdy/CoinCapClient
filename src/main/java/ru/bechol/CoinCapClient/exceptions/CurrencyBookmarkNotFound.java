package ru.bechol.CoinCapClient.exceptions;

import java.util.NoSuchElementException;

/**
 * Класс CurrencyBookmarkNotFound.
 * Кастомная ошибка для отсутствующей закладки.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
public class CurrencyBookmarkNotFound extends NoSuchElementException {

    public CurrencyBookmarkNotFound(String message) {
        super(message);
    }
}
