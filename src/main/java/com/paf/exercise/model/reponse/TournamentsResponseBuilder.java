package com.paf.exercise.model.reponse;

import com.paf.exercise.datasource.provider.database.model.DbTournaments;
import java.util.ArrayList;
import java.util.List;
import com.paf.exercise.model.Currency;
import com.paf.exercise.model.Tournament;
import org.springframework.stereotype.Component;

@Component
public class TournamentsResponseBuilder {

  public List<Tournament> build(List<DbTournaments> dbTournamentsList) {
    List<Tournament> tournaments = new ArrayList<>();

    for (DbTournaments dbTournament : dbTournamentsList) {
      Tournament tournament = Tournament.builder()
          .id(dbTournament.getId())
          .name(dbTournament.getName())
          .currency(Currency.builder()
              .id(dbTournament.getCurrency().getId())
              .name(dbTournament.getCurrency().getName())
              .build())
          .build();
      tournaments.add(tournament);
    }
    return tournaments;
  }

  public Tournament build(DbTournaments dbTournament) {
    return Tournament.builder()
        .id(dbTournament.getId())
        .name(dbTournament.getName())
        .currency(Currency.builder()
            .id(dbTournament.getCurrency().getId())
            .name(dbTournament.getCurrency().getName())
            .build())
        .build();
  }

}