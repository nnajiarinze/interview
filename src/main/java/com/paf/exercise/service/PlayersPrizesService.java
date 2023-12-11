package com.paf.exercise.service;

import java.util.List;
import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.model.DbPlayersPrizes;
import com.paf.exercise.model.request.PostReqPlayerPrize;

public interface PlayersPrizesService {

  DbPlayersPrizes createPlayerPrize(PostReqPlayerPrize playerPrize, DbPlayers dbPlayer);

  List<DbPlayersPrizes> getPlayerPrizesByPlayerId(Long playerId);

}
