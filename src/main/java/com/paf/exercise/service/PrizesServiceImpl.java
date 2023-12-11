package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbPrizes;
import com.paf.exercise.model.request.PostReqPrize;
import java.util.Optional;
import com.paf.exercise.datasource.provider.database.repository.PrizesRepository;
import com.paf.exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrizesServiceImpl implements PrizesService {

  @Autowired
  private PrizesRepository prizesRepository;


  @Override
  public DbPrizes createPrize(PostReqPrize postReqPrize) {
    DbPrizes prize = DbPrizes.builder()
        .name(postReqPrize.getName())
        .amount(postReqPrize.getAmount())
        .tournamentId(postReqPrize.getTournamentId())
        .build();
    return prizesRepository.save(prize);
  }

  @Override
  public DbPrizes getPrizeById(Long prizeId) {

    Optional<DbPrizes> prizesOptional = prizesRepository.findById(prizeId);

    if (prizesOptional.isPresent()) {
      return prizesOptional.get();
    } else {
      throw new ResourceNotFoundException("Prize not found with id : " + prizeId);
    }
  }
}