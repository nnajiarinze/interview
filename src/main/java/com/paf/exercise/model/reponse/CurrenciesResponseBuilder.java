package com.paf.exercise.model.reponse;

import java.util.ArrayList;
import java.util.List;
import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import com.paf.exercise.model.Currency;
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