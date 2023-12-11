package net.javaguides.springboot.datasource.provider.database.repository;

import net.javaguides.springboot.datasource.provider.database.model.DbCurrencies;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrenciesRepository extends JpaRepository<DbCurrencies, Long> {

}
