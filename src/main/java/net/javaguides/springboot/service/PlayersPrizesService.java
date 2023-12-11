package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayersPrizes;
import net.javaguides.springboot.model.request.PostReqPlayerPrize;

public interface PlayersPrizesService {

  DbPlayersPrizes createPlayerPrize(PostReqPlayerPrize playerPrize);

  List<DbPlayersPrizes> getPlayerPrizesByPlayerId(Long playerId);

}
