package net.javaguides.springboot.datasource.provider.database.repository;

import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbTournamentsPlayers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentsPlayersRepository extends JpaRepository<DbTournamentsPlayers, Long> {

  List<DbTournamentsPlayers> getDbTournamentsPlayersByTournamentId(Long tournamentId);
}
