package net.javaguides.springboot.model.reponse;

import java.util.ArrayList;
import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.model.Currency;
import net.javaguides.springboot.model.Player;
import org.springframework.stereotype.Component;

@Component
public class CurrenciesResponseBuilder {

  public List<Currency> build(List<DbCurrencies> dbCurrenciesList) {
    List<Currency> currencies = new ArrayList<>();

    for (DbCurrencies dbCurrency : dbCurrenciesList) {
      Currency currency = Currency.builder()
          .id(dbCurrency.getId())
          .name(dbCurrency.getName())
          .build();

      currencies.add(currency);
    }
    return currencies;
  }

  public Currency build(DbCurrencies dbCurrency) {
    return Currency.builder()
        .id(dbCurrency.getId())
        .name(dbCurrency.getName())
        .build();
  }
}