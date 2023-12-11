package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayersPrizes;
import net.javaguides.springboot.datasource.provider.database.repository.PlayersPrizesRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.request.PostReqPlayerPrize;
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