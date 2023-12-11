package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import net.javaguides.springboot.constants.SwaggerConstants;
import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.model.DbTournaments;
import net.javaguides.springboot.datasource.provider.database.model.DbTournamentsPlayers;
import net.javaguides.springboot.model.Player;
import net.javaguides.springboot.model.Tournament;
import net.javaguides.springboot.model.TournamentPlayer;
import net.javaguides.springboot.model.reponse.PlayersResponseBuilder;
import net.javaguides.springboot.model.reponse.TournamentsResponseBuilder;
import net.javaguides.springboot.model.request.PostReqTournament;
import net.javaguides.springboot.model.request.PostReqTournamentPlayer;
import net.javaguides.springboot.service.CurrenciesService;
import net.javaguides.springboot.service.PlayersService;
import net.javaguides.springboot.service.TournamentsPlayersService;
import net.javaguides.springboot.service.TournamentsService;
import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private TournamentsService tournamentsService;

  @Autowired
  private TournamentsPlayersService tournamentsPlayersService;

  @Autowired
  private PlayersService playersService;

  @Autowired
  private CurrenciesService currenciesService;

  public TournamentsController(TournamentsResponseBuilder tournamentsResponseBuilder,
      PlayersResponseBuilder playersResponseBuilder) {
    this.tournamentsResponseBuilder = tournamentsResponseBuilder;
    this.playersResponseBuilder = playersResponseBuilder;
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
