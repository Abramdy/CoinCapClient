package ru.bechol.CoinCapClient.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс CurrencyBookmark.
 * Реализация закладок для валют.
 *
 * @author Oleg Bech.
 * @email oleg071984@gmail.com
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_bookmark")
public class CurrencyBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "currency_name", unique = true)
    private String currencyName;

    public CurrencyBookmark(String currencyName) {
        this.currencyName = currencyName;
    }
}
