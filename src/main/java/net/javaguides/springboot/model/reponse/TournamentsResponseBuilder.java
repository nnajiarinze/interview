package net.javaguides.springboot.model.reponse;

import java.util.ArrayList;
import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbTournaments;
import net.javaguides.springboot.datasource.provider.database.model.DbTournamentsPlayers;
import net.javaguides.springboot.model.Currency;
import net.javaguides.springboot.model.Tournament;
import net.javaguides.springboot.model.TournamentPlayer;
import net.javaguides.springboot.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
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