package ru.bechol.CoinCapClient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bechol.CoinCapClient.models.CurrencyBookmark;

import java.util.Optional;

/**
 * Интерфейс BookmarkRepository.
 * Слой доступа к данным.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Repository
public interface BookmarkRepository extends JpaRepository<CurrencyBookmark, Long> {

    Optional<String> findByCurrencyName(String currencyName);
}
