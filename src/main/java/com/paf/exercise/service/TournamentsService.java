package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import com.paf.exercise.datasource.provider.database.model.DbTournaments;
import com.paf.exercise.model.request.PostReqTournament;

public interface TournamentsService {

  DbTournaments createTournament(PostReqTournament tournaments, DbCurrencies dbCurrencies);

  DbTournaments getTournamentById(Long tournamentId);

}
