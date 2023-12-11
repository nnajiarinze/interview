package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayersPrizes;
import net.javaguides.springboot.model.request.PostReqPlayerPrize;

public interface PlayersPrizesService {

  DbPlayersPrizes createPlayerPrize(PostReqPlayerPrize playerPrize, DbPlayers dbPlayer);

  List<DbPlayersPrizes> getPlayerPrizesByPlayerId(Long playerId);

}
