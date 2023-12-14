package com.paf.exercise.controller;

import com.paf.exercise.constants.SwaggerConstants;
import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.model.DbTournaments;
import com.paf.exercise.datasource.provider.database.model.DbTournamentsPlayers;
import com.paf.exercise.model.TournamentPlayer;
import com.paf.exercise.model.reponse.PlayersResponseBuilder;
import com.paf.exercise.model.request.PostReqTournamentPlayer;
import com.paf.exercise.service.CurrenciesService;
import com.paf.exercise.service.PlayersService;
import com.paf.exercise.service.TournamentsPlayersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import com.paf.exercise.model.Player;
import com.paf.exercise.model.Tournament;
import com.paf.exercise.model.reponse.TournamentsResponseBuilder;
import com.paf.exercise.model.request.PostReqTournament;
import com.paf.exercise.service.TournamentsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tournaments")
public class TournamentsController {

  private final TournamentsResponseBuilder tournamentsResponseBuilder;
  private final PlayersResponseBuilder playersResponseBuilder;

  private TournamentsService tournamentsService;

  private TournamentsPlayersService tournamentsPlayersService;

  private PlayersService playersService;

  private CurrenciesService currenciesService;

  public TournamentsController(TournamentsResponseBuilder tournamentsResponseBuilder,
      PlayersResponseBuilder playersResponseBuilder, TournamentsService tournamentsService,
      TournamentsPlayersService tournamentsPlayersService, PlayersService playersService,
      CurrenciesService currenciesService) {
    this.tournamentsResponseBuilder = tournamentsResponseBuilder;
    this.playersResponseBuilder = playersResponseBuilder;
    this.tournamentsService = tournamentsService;
    this.tournamentsPlayersService = tournamentsPlayersService;
    this.playersService = playersService;
    this.currenciesService = currenciesService;
  }


  @GetMapping("/{id}")
  public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
    DbTournaments dbTournament = tournamentsService.getTournamentById(id);
    return ResponseEntity.ok().body(tournamentsResponseBuilder.build(dbTournament));
  }

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(description = SwaggerConstants.CREATE_TOURNAMENT, summary = SwaggerConstants.CREATE_TOURNAMENT)
  @ApiResponse(description = "Created", responseCode = "201",
      content = @Content(schema = @Schema(implementation = Tournament.class)))
  public ResponseEntity<Tournament> createTournament(@RequestBody PostReqTournament tournament) {

    DbCurrencies dbCurrencies = currenciesService.getCurrencyById(tournament.getCurrencyId());

    DbTournaments dbTournament = tournamentsService.createTournament(tournament, dbCurrencies);

    return ResponseEntity.ok()
        .body(tournamentsResponseBuilder.build(tournamentsService.getTournamentById(
            dbTournament.getId())));
  }

  @PostMapping(value = "/players", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(description = SwaggerConstants.CREATE_TOURNAMENT, summary = SwaggerConstants.CREATE_TOURNAMENT)
  @ApiResponse(description = "Created", responseCode = "201",
      content = @Content(schema = @Schema(implementation = Tournament.class)))
  public ResponseEntity<TournamentPlayer> createTournamentPlayer(
      @RequestBody PostReqTournamentPlayer postReqTournamentPlayer) {
    DbPlayers dbPlayers = playersService.getPlayerById(postReqTournamentPlayer.getPlayerId());
    DbTournamentsPlayers dbTournamentsPlayers = tournamentsPlayersService.createTournamentPlayer(
        postReqTournamentPlayer, dbPlayers);

    return ResponseEntity.ok()
        .body(playersResponseBuilder.buildTournamentPlayers(dbTournamentsPlayers));
  }

  @GetMapping("/{id}/players")
  public ResponseEntity<List<Player>> getTournamentPlayers(@PathVariable Long id) {
    List<DbTournamentsPlayers> dbTournamentsPlayers =
        tournamentsPlayersService.getTournamentPlayersByTournamentId(id);
    return ResponseEntity.ok()
        .body(playersResponseBuilder.buildTournamentPlayersList(dbTournamentsPlayers));
  }

}
