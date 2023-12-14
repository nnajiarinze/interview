package com.paf.exercise.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.openMocks;

import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.model.DbTournaments;
import com.paf.exercise.datasource.provider.database.repository.PlayersRepository;
import com.paf.exercise.exception.ResourceNotFoundException;
import com.paf.exercise.model.request.PostReqPlayer;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class PlayersServiceImplTest {

  @InjectMocks
  private PlayersServiceImpl playersServiceImpl;


  @Mock
  private PlayersRepository playersRepository;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }


  @Test
  void createPlayerTest() {
    PostReqPlayer postReqPlayer = PostReqPlayer.builder()
        .name("player")
        .build();

    playersServiceImpl.createPlayer(postReqPlayer);
    verify(playersRepository, times(1)).save(
        argThat(
            c -> Objects.equals(c.getName(), postReqPlayer.getName())));
    verifyNoMoreInteractions(playersRepository);
  }

  @Test
  void getPlayerById() {

    DbPlayers dbPlayers = DbPlayers.builder()
        .name("test")
        .id(1L)
        .build();
    Optional<DbPlayers> optionalDbPlayers = Optional.of(dbPlayers);

    doReturn(optionalDbPlayers).when(playersRepository).findById(1L);

    playersServiceImpl.getPlayerById(1L);
    verify(playersRepository, times(1)).findById(
        argThat(
            id -> Objects.equals(id, 1L)
        ));
    verifyNoMoreInteractions(playersRepository);
  }


  @Test
  void getPlayerByWrongIdTest() {
    doReturn(Optional.empty()).when(playersRepository).findById(1L);
    assertThrows(ResourceNotFoundException.class,
        () -> playersServiceImpl.getPlayerById(1L));
    verify(playersRepository, times(1)).findById(
        argThat(
            id -> Objects.equals(id, 1L)
        ));
    verifyNoMoreInteractions(playersRepository);
  }


}