package net.javaguides.springboot.controller;

import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayersPrizes;
import net.javaguides.springboot.model.Player;
import net.javaguides.springboot.model.PlayerPrize;
import net.javaguides.springboot.model.Prize;
import net.javaguides.springboot.model.reponse.PlayersResponseBuilder;
import net.javaguides.springboot.model.request.PostReqPlayer;
import net.javaguides.springboot.model.request.PostReqPlayerPrize;
import net.javaguides.springboot.service.PlayersPrizesService;
import net.javaguides.springboot.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/players")
public class PlayersController {

  private final PlayersResponseBuilder playersResponseBuilder;
  @Autowired
  private PlayersService playersService;

  @Autowired
  private PlayersPrizesService playersPrizesService;

  public PlayersController(PlayersResponseBuilder playersResponseBuilder) {
    this.playersResponseBuilder = playersResponseBuilder;
  }


  @GetMapping("/{id}")
  public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
    DbPlayers dbPlayer = playersService.getPlayerById(id);
    return ResponseEntity.ok().body(playersResponseBuilder.build(dbPlayer));
  }

  @PostMapping("")
  public ResponseEntity<Player> createPlayer(@RequestBody PostReqPlayer player) {
    DbPlayers dbPlayer = playersService.createPlayer(player);
    return ResponseEntity.ok().body(playersResponseBuilder.build(dbPlayer));
  }

  @GetMapping("/{playerId}/prizes")
  public ResponseEntity<List<Prize>> getPlayerPrizes(@PathVariable Long playerId) {
    List<DbPlayersPrizes> dbPlayersPrizes = playersPrizesService.getPlayerPrizesByPlayerId(
        playerId);
    return ResponseEntity.ok().body(playersResponseBuilder.buildPlayerPrizeList(dbPlayersPrizes));
  }

  @PostMapping("/prizes")
  public ResponseEntity<PlayerPrize> createPlayerPrize(@RequestBody PostReqPlayerPrize playerPrize) {
    DbPlayersPrizes dbPlayersPrize = playersPrizesService.createPlayerPrize(playerPrize);
    return ResponseEntity.ok().body(playersResponseBuilder.buildPlayerPrize(dbPlayersPrize));
  }
}