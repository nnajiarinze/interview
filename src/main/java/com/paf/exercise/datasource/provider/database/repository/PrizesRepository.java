package com.paf.exercise.datasource.provider.database.repository;

import com.paf.exercise.datasource.provider.database.model.DbPrizes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizesRepository extends JpaRepository<DbPrizes, Long> {

}
