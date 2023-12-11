package net.javaguides.springboot.datasource.provider.database.repository;

import net.javaguides.springboot.datasource.provider.database.model.DbTournaments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentsRepository extends JpaRepository<DbTournaments, Long> {


}
