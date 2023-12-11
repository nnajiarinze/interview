package net.javaguides.springboot.datasource.provider.database.repository;

import java.util.List;
import java.util.Optional;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayersPrizes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersPrizesRepository extends JpaRepository<DbPlayersPrizes, Long> {

  List<DbPlayersPrizes> getDbPlayersPrizesByPlayerId(Long playerId);
}
