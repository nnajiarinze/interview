package net.javaguides.springboot.service;

import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.model.request.PostReqPlayer;

public interface PlayersService {

  DbPlayers createPlayer(PostReqPlayer player);

  DbPlayers getPlayerById(Long playerId);

}
