package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.model.DbTournamentsPlayers;
import com.paf.exercise.model.request.PostReqTournamentPlayer;
import java.util.List;
import com.paf.exercise.datasource.provider.database.repository.TournamentsPlayersRepository;
import com.paf.exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TournamentsPlayersServiceImpl implements TournamentsPlayersService {

  @Autowired
  private TournamentsPlayersRepository tournamentsPlayersRepository;


  @Override
  public DbTournamentsPlayers createTournamentPlayer(
      PostReqTournamentPlayer postReqTournamentPlayer, DbPlayers dbPlayer) {
    DbTournamentsPlayers dbTournamentsPlayer = DbTournamentsPlayers.builder()
        .player(dbPlayer)
        .tournamentId(postReqTournamentPlayer.getTournamentId()).build();

    return tournamentsPlayersRepository.save(dbTournamentsPlayer);
  }


  @Override
  public List<DbTournamentsPlayers> getTournamentPlayersByTournamentId(Long tournamentId) {

    List<DbTournamentsPlayers> tournamentsPlayersList =
        tournamentsPlayersRepository.getDbTournamentsPlayersByTournamentId(
            tournamentId);

    if (!tournamentsPlayersList.isEmpty()) {
      return tournamentsPlayersList;
    } else {
      throw new ResourceNotFoundException(
          "Tournamenet Players not found with tournament id : " + tournamentId);
    }
  }
}