package com.paf.exercise.service;

import static com.paf.exercise.constants.ErrorConstants.PLAYER_NOT_FOUND;

import com.paf.exercise.datasource.provider.database.repository.PlayersRepository;
import java.util.Optional;
import com.paf.exercise.exception.ResourceNotFoundException;
import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.model.request.PostReqPlayer;
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
      throw new ResourceNotFoundException(String.format(PLAYER_NOT_FOUND, playerId));
    }
  }
}