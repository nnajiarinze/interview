package net.javaguides.springboot.model.reponse;

import java.util.ArrayList;
import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbPrizes;
import net.javaguides.springboot.model.Prize;
import net.javaguides.springboot.service.TournamentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrizesResponseBuilder {

  @Autowired
  TournamentsService tournamentsService;
  private final TournamentsResponseBuilder tournamentsResponseBuilder;

  public PrizesResponseBuilder(TournamentsResponseBuilder tournamentsResponseBuilder) {
    this.tournamentsResponseBuilder = tournamentsResponseBuilder;
  }

  public List<Prize> build(List<DbPrizes> dbPrizes) {
    List<Prize> prizes = new ArrayList<>();

    for (DbPrizes dbPrize : dbPrizes) {
      Prize player = Prize.builder()
          .id(dbPrize.getId())
          .name(dbPrize.getName())
          .amount(dbPrize.getAmount())
          .tournament(tournamentsResponseBuilder.build(
              tournamentsService.getTournamentById(dbPrize.getTournamentId())))
          .build();

      prizes.add(player);
    }
    return prizes;
  }

  public Prize build(DbPrizes dbPrize) {
    return Prize.builder()
        .id(dbPrize.getId())
        .name(dbPrize.getName())
        .amount(dbPrize.getAmount())
        .tournament(tournamentsResponseBuilder.build(
            tournamentsService.getTournamentById(dbPrize.getTournamentId())))
        .build();
  }
}