package com.paf.exercise.datasource.provider.database.repository;

import com.paf.exercise.datasource.provider.database.model.DbTournamentsPlayers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentsPlayersRepository extends JpaRepository<DbTournamentsPlayers, Long> {

  List<DbTournamentsPlayers> getDbTournamentsPlayersByTournamentId(Long tournamentId);
}
