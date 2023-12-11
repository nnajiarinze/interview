package com.paf.exercise.datasource.provider.database.repository;

import com.paf.exercise.datasource.provider.database.model.DbPlayersPrizes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersPrizesRepository extends JpaRepository<DbPlayersPrizes, Long> {

  List<DbPlayersPrizes> getDbPlayersPrizesByPlayerId(Long playerId);
}
