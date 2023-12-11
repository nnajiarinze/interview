package com.paf.exercise.datasource.provider.database.repository;

import com.paf.exercise.datasource.provider.database.model.DbCurrencies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrenciesRepository extends JpaRepository<DbCurrencies, Long> {

}
