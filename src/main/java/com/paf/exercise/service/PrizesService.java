package com.paf.exercise.service;

import com.paf.exercise.datasource.provider.database.model.DbPrizes;
import com.paf.exercise.model.request.PostReqPrize;

public interface PrizesService {

  DbPrizes createPrize(PostReqPrize prize);

  DbPrizes getPrizeById(Long prizeId);

}
