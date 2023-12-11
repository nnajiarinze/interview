package com.paf.exercise.datasource.provider.database.repository;

import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<DbPlayers, Long> {

}
