package net.javaguides.springboot.service;

import java.util.Optional;
import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.repository.CurrenciesRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.request.PostReqCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CurrenciesServiceImpl implements CurrenciesService {

  @Autowired
  private CurrenciesRepository currenciesRepository;

  @Override
  public DbCurrencies createCurrency(PostReqCurrency postReqCurrency) {
    DbCurrencies dbCurrency = DbCurrencies.builder()
        .name(postReqCurrency.getName())
        .build();
    return currenciesRepository.save(dbCurrency);
  }

  @Override
  public DbCurrencies getCurrencyById(Long id) {

    Optional<DbCurrencies> currenciesOptional = currenciesRepository.findById(id);

    if (currenciesOptional.isPresent()) {
      return currenciesOptional.get();
    } else {
      throw new ResourceNotFoundException("Currency not found with id : " + id);
    }
  }
}