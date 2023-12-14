package com.paf.exercise.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.openMocks;

import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import com.paf.exercise.datasource.provider.database.model.DbTournaments;
import com.paf.exercise.datasource.provider.database.repository.TournamentsRepository;
import com.paf.exercise.exception.ResourceNotFoundException;
import com.paf.exercise.model.request.PostReqTournament;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TournamentsServiceImplTest {

  @InjectMocks
  private TournamentsServiceImpl tournamentsServiceImpl;

  @Mock
  private TournamentsRepository tournamentsRepository;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }


  @Test
  void createTournamentTest() {
    PostReqTournament postReqTournament = PostReqTournament.builder()
        .name("test")
        .build();
    DbCurrencies dbCurrencies = DbCurrencies.builder()
        .id(1L)
        .name("SEK")
        .build();

    tournamentsServiceImpl.createTournament(postReqTournament, dbCurrencies);
    verify(tournamentsRepository, times(1)).save(
        argThat(
            c -> Objects.equals(c.getCurrency().getName(), dbCurrencies.getName()) &&
                c.getName().equals(postReqTournament.getName())

        ));
    verifyNoMoreInteractions(tournamentsRepository);
  }

  @Test
  void getTournamentByIdTest() {

    DbCurrencies dbCurrencies = DbCurrencies.builder()
        .id(1L)
        .name("SEK")
        .build();
    DbTournaments dbTournaments = DbTournaments.builder()
        .name("test")
        .id(1L)
        .currency(dbCurrencies)
        .build();
    Optional<DbTournaments> optionalDbTournament = Optional.of(dbTournaments);

    doReturn(optionalDbTournament).when(tournamentsRepository).findById(1L);

    tournamentsServiceImpl.getTournamentById(1L);
    verify(tournamentsRepository, times(1)).findById(
        argThat(
            id -> Objects.equals(id, 1L)
        ));
    verifyNoMoreInteractions(tournamentsRepository);
  }


  @Test
  void getTournamentByWrongIdTest() {
    doReturn(Optional.empty()).when(tournamentsRepository).findById(1L);
    assertThrows(ResourceNotFoundException.class,
        () -> tournamentsServiceImpl.getTournamentById(1L));
    verify(tournamentsRepository, times(1)).findById(
        argThat(
            id -> Objects.equals(id, 1L)
        ));
    verifyNoMoreInteractions(tournamentsRepository);
  }

}