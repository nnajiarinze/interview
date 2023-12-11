package com.paf.exercise.controller;

import java.util.List;
import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.model.DbPlayersPrizes;
import com.paf.exercise.model.Player;
import com.paf.exercise.model.PlayerPrize;
import com.paf.exercise.model.Prize;
import com.paf.exercise.model.reponse.PlayersResponseBuilder;
import com.paf.exercise.model.request.PostReqPlayer;
import com.paf.exercise.model.request.PostReqPlayerPrize;
import com.paf.exercise.service.PlayersPrizesService;
import com.paf.exercise.service.PlayersService;
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
    DbPlayers dbPlayer = playersService.getPlayerById(playerPrize.getPlayerId());
    DbPlayersPrizes dbPlayersPrize = playersPrizesService.createPlayerPrize(playerPrize, dbPlayer);
    return ResponseEntity.ok().body(playersResponseBuilder.buildPlayerPrize(dbPlayersPrize));
  }
}
