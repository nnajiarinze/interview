package net.javaguides.springboot.controller;

import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.model.Currency;
import net.javaguides.springboot.model.Player;
import net.javaguides.springboot.model.reponse.CurrenciesResponseBuilder;
import net.javaguides.springboot.model.request.PostReqCurrency;
import net.javaguides.springboot.model.request.PostReqPlayer;
import net.javaguides.springboot.service.CurrenciesService;
import net.javaguides.springboot.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/currencies")
public class CurrenciesController {

  private final CurrenciesResponseBuilder currenciesResponseBuilder;
  @Autowired
  private CurrenciesService currenciesService;

  public CurrenciesController(CurrenciesResponseBuilder currenciesResponseBuilder) {
    this.currenciesResponseBuilder = currenciesResponseBuilder;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
    DbCurrencies dbPlayer = currenciesService.getCurrencyById(id);
    return ResponseEntity.ok().body(currenciesResponseBuilder.build(dbPlayer));
  }

  @PostMapping("")
  public ResponseEntity<Currency> createCurrency(@RequestBody PostReqCurrency currency) {
    DbCurrencies dbCurrencies = currenciesService.createCurrency(currency);
    return ResponseEntity.ok().body(currenciesResponseBuilder.build(dbCurrencies));
  }

}
