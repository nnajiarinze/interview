package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.model.request.PostReqPlayer;

public interface PlayersService {

  DbPlayers createPlayer(PostReqPlayer player);

  DbPlayers getPlayerById(Long playerId);

}
