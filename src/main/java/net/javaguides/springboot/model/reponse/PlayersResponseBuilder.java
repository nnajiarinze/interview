package net.javaguides.springboot.model.reponse;

import java.util.ArrayList;
import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayersPrizes;
import net.javaguides.springboot.datasource.provider.database.model.DbTournamentsPlayers;
import net.javaguides.springboot.model.Player;
import net.javaguides.springboot.model.PlayerPrize;
import net.javaguides.springboot.model.Prize;
import net.javaguides.springboot.model.TournamentPlayer;
import net.javaguides.springboot.service.PrizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayersResponseBuilder {

  @Autowired
  private final PrizesService prizesService;

  private final PrizesResponseBuilder prizesResponseBuilder;

  public PlayersResponseBuilder(PrizesService prizesService,
      PrizesResponseBuilder prizesResponseBuilder) {
    this.prizesService = prizesService;
    this.prizesResponseBuilder = prizesResponseBuilder;
  }

  public List<Player> build(List<DbPlayers> dbPlayersList) {
    List<Player> players = new ArrayList<>();

    for (DbPlayers dbPlayer : dbPlayersList) {
      Player player = Player.builder()
          .id(dbPlayer.getId())
          .name(dbPlayer.getName())
          .build();

      players.add(player);
    }
    return players;
  }

  public Player build(DbPlayers dbPlayer) {
    return Player.builder()
        .id(dbPlayer.getId())
        .name(dbPlayer.getName())
        .build();
  }

  public List<PlayerPrize> buildPrizes(List<DbPlayersPrizes> dbPlayersPrizesList) {

    List<PlayerPrize> playerPrizes = new ArrayList<>();
    for (DbPlayersPrizes dbPlayersPrize : dbPlayersPrizesList) {
      PlayerPrize playerPrize = PlayerPrize.builder()
          .id(dbPlayersPrize.getId())
          .player(build(dbPlayersPrize.getPlayer()))
          .prize(
              prizesResponseBuilder.build(prizesService.getPrizeById(dbPlayersPrize.getPrizeId())))
          .build();

      playerPrizes.add(playerPrize);
    }
    return playerPrizes;
  }

  public PlayerPrize buildPlayerPrize(DbPlayersPrizes dbPlayersPrize) {
    return PlayerPrize.builder()
        .id(dbPlayersPrize.getId())
        .player(build(dbPlayersPrize.getPlayer()))
        .prize(prizesResponseBuilder.build(prizesService.getPrizeById(dbPlayersPrize.getPrizeId())))
        .build();
  }

  public List<Prize> buildPlayerPrizeList(List<DbPlayersPrizes> dbPlayersPrizesList) {

    List<Prize> playerPrizes = new ArrayList<>();
    for (DbPlayersPrizes dbPlayersPrize : dbPlayersPrizesList) {
      Prize playerPrize =
          prizesResponseBuilder.build(prizesService.getPrizeById(dbPlayersPrize.getPrizeId()));

      playerPrizes.add(playerPrize);
    }
    return playerPrizes;
  }

  public TournamentPlayer buildTournamentPlayers(DbTournamentsPlayers dbTournamentsPlayers) {

    return TournamentPlayer.builder()
        .tournamentId(dbTournamentsPlayers.getTournamentId())
        .player(build(dbTournamentsPlayers.getPlayer()))
        .build();
  }

  public List<Player> buildTournamentPlayersList(List<DbTournamentsPlayers> dbTournamentsPlayersList) {
    List<Player> tournamentPlayers = new ArrayList<>();
    for (DbTournamentsPlayers dbTournamentsPlayer : dbTournamentsPlayersList) {
      Player player = build(dbTournamentsPlayer.getPlayer());

      tournamentPlayers.add(player);
    }
    return tournamentPlayers;
  }
}