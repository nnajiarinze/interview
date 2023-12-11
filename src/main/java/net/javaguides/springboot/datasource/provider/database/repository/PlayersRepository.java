package net.javaguides.springboot.datasource.provider.database.repository;

import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<DbPlayers, Long> {

}
