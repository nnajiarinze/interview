package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import com.paf.exercise.model.request.PostReqCurrency;

public interface CurrenciesService {

  DbCurrencies createCurrency(PostReqCurrency currency);

  DbCurrencies getCurrencyById(Long id);
}
