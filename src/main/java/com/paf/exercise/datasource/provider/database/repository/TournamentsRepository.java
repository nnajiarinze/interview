package com.paf.exercise.datasource.provider.database.repository;

import com.paf.exercise.datasource.provider.database.model.DbTournaments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentsRepository extends JpaRepository<DbTournaments, Long> {


}
