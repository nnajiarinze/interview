package net.javaguides.springboot.service;

import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.model.request.PostReqCurrency;

public interface CurrenciesService {

  DbCurrencies createCurrency(PostReqCurrency currency);

  DbCurrencies getCurrencyById(Long id);
}
