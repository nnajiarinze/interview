package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import com.paf.exercise.datasource.provider.database.model.DbTournaments;
import com.paf.exercise.exception.ResourceNotFoundException;
import com.paf.exercise.model.request.PostReqTournament;
import java.util.Optional;
import com.paf.exercise.datasource.provider.database.repository.TournamentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TournamentsServiceImpl implements TournamentsService {

  @Autowired
  private TournamentsRepository tournamentsRepository;


  @Override
  public DbTournaments createTournament(PostReqTournament postReqTournament,
      DbCurrencies dbCurrencies) {
    DbTournaments dbTournament = DbTournaments.builder()
        .name(postReqTournament.getName())
        .currency(dbCurrencies)
        .build();

    return tournamentsRepository.save(dbTournament);
  }

  @Override
  public DbTournaments getTournamentById(Long tournamentId) {

    Optional<DbTournaments> tournamentsOptional = tournamentsRepository.findById(tournamentId);

    if (tournamentsOptional.isPresent()) {
      return tournamentsOptional.get();
    } else {
      throw new ResourceNotFoundException("Tournamenet not found with id : " + tournamentId);
    }
  }
}