package net.javaguides.springboot.service;

import java.util.Optional;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.model.DbPrizes;
import net.javaguides.springboot.datasource.provider.database.repository.PlayersRepository;
import net.javaguides.springboot.datasource.provider.database.repository.PrizesRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.request.PostReqPlayer;
import net.javaguides.springboot.model.request.PostReqPrize;
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