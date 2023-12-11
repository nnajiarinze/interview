package net.javaguides.springboot.service;

import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbTournaments;
import net.javaguides.springboot.model.request.PostReqTournament;

public interface TournamentsService {

  DbTournaments createTournament(PostReqTournament tournaments, DbCurrencies dbCurrencies);

  DbTournaments getTournamentById(Long tournamentId);

}
