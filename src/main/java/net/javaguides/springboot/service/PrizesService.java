package net.javaguides.springboot.service;

import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbPrizes;
import net.javaguides.springboot.datasource.provider.database.model.DbTournaments;
import net.javaguides.springboot.model.request.PostReqPrize;
import net.javaguides.springboot.model.request.PostReqTournament;

public interface PrizesService {

  DbPrizes createPrize(PostReqPrize prize);

  DbPrizes getPrizeById(Long prizeId);

}
