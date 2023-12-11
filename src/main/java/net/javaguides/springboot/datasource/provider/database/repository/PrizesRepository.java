package net.javaguides.springboot.datasource.provider.database.repository;

import net.javaguides.springboot.datasource.provider.database.model.DbPrizes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizesRepository extends JpaRepository<DbPrizes, Long> {

}
