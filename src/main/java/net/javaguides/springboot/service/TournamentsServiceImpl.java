package net.javaguides.springboot.service;

import java.util.Optional;
import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbTournaments;
import net.javaguides.springboot.datasource.provider.database.repository.TournamentsRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.request.PostReqTournament;
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