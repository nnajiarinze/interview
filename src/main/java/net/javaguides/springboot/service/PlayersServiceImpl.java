package net.javaguides.springboot.service;

import java.util.Optional;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.repository.PlayersRepository;
import net.javaguides.springboot.model.request.PostReqPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayersServiceImpl implements PlayersService {

  @Autowired
  private PlayersRepository playersRepository;

  @Override
  public DbPlayers createPlayer(PostReqPlayer postReqPlayer) {
    DbPlayers player = DbPlayers.builder()
        .name(postReqPlayer.getName())
        .build();
    return playersRepository.save(player);
  }

  @Override
  public DbPlayers getPlayerById(Long playerId) {

    Optional<DbPlayers> playersOptional = this.playersRepository.findById(playerId);

    if (playersOptional.isPresent()) {
      return playersOptional.get();
    } else {
      throw new ResourceNotFoundException("Player not found with id : " + playerId);
    }
  }
}