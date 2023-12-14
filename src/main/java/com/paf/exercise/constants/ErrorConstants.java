package com.paf.exercise.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorConstants {


  public static final String TOURNAMENT_NOT_FOUND =
      "Tournament id %d not found";

  public static final String PLAYER_NOT_FOUND =
      "Player id %d not found";

}

