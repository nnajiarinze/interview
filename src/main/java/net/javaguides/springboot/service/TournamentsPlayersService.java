package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbTournamentsPlayers;
import net.javaguides.springboot.model.request.PostReqTournamentPlayer;

public interface TournamentsPlayersService {

  DbTournamentsPlayers createTournamentPlayer(PostReqTournamentPlayer tournamentPlayer);

  List<DbTournamentsPlayers> getTournamentPlayersByTournamentId(Long tournamentId);

}
