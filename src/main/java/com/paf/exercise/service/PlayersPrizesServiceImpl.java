package com.paf.exercise.service;

import com.paf.exercise.model.request.PostReqPlayerPrize;
import java.util.List;
import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.model.DbPlayersPrizes;
import com.paf.exercise.datasource.provider.database.repository.PlayersPrizesRepository;
import com.paf.exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayersPrizesServiceImpl implements PlayersPrizesService {

  @Autowired
  private PlayersPrizesRepository playersPrizesRepository;


  @Override
  public DbPlayersPrizes createPlayerPrize(PostReqPlayerPrize postReqPlayerPrize,
      DbPlayers dbPlayer) {
    DbPlayersPrizes player = DbPlayersPrizes.builder()
        .player(dbPlayer)
        .prizeId(postReqPlayerPrize.getPrizeId())
        .build();
    return playersPrizesRepository.save(player);
  }

  @Override
  public List<DbPlayersPrizes> getPlayerPrizesByPlayerId(Long playerId) {

    List<DbPlayersPrizes> playersPrizesList = playersPrizesRepository.getDbPlayersPrizesByPlayerId(
        playerId);

    if (!playersPrizesList.isEmpty()) {
      return playersPrizesList;
    } else {
      throw new ResourceNotFoundException("Prizes not found for player with id : " + playerId);
    }
  }
}