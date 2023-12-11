package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.model.DbTournamentsPlayers;
import com.paf.exercise.model.request.PostReqTournamentPlayer;
import java.util.List;

public interface TournamentsPlayersService {

  DbTournamentsPlayers createTournamentPlayer(PostReqTournamentPlayer tournamentPlayer,
      DbPlayers dbPlayer);

  List<DbTournamentsPlayers> getTournamentPlayersByTournamentId(Long tournamentId);

}
