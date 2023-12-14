package com.paf.exercise.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.openMocks;

import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.model.Player;
import com.paf.exercise.model.reponse.PlayersResponseBuilder;
import com.paf.exercise.model.request.PostReqPlayer;
import com.paf.exercise.service.PlayersPrizesService;
import com.paf.exercise.service.PlayersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class PlayersControllerTest {


  private PlayersController playersController;

  private PlayersResponseBuilder playersResponseBuilder;

  private PlayersPrizesService playersPrizesService;

  private PlayersService playersService;


  @BeforeEach
  void setup() {
  playersResponseBuilder = mock(PlayersResponseBuilder.class);
  playersService = mock(PlayersService.class);
  playersPrizesService = mock(PlayersPrizesService.class);
    playersController = new PlayersController(playersResponseBuilder, playersService, playersPrizesService);
  }

  @Test
  void createPlayerTest() {

    PostReqPlayer postReqPlayer = PostReqPlayer.builder()
        .name("player")
        .build();
    DbPlayers dbPlayers = DbPlayers.builder()
        .id(1L)
        .name("player")
        .build();

    Player responsePlayer = Player.builder()
        .id(dbPlayers.getId())
        .name(dbPlayers.getName())
        .build();

    doReturn(dbPlayers).when(playersService).createPlayer(any());
    doReturn(responsePlayer).when(playersResponseBuilder).build(any(DbPlayers.class));
    ResponseEntity<Player> responseEntity = playersController
        .createPlayer(postReqPlayer);

    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    assertEquals(responseEntity.getBody().getId(), responsePlayer.getId());

  }


  @Test
  void getPlayerByIdTest() {

    DbPlayers dbPlayers = DbPlayers.builder()
        .id(1L)
        .name("player")
        .build();

    Player responsePlayer = Player.builder()
        .id(dbPlayers.getId())
        .name(dbPlayers.getName())
        .build();

    doReturn(dbPlayers).when(playersService).getPlayerById(any());


    doReturn(responsePlayer).when(playersResponseBuilder).build(any(DbPlayers.class));
    ResponseEntity<Player> responseEntity = playersController
        .getPlayerById(1L);

    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

  }
}