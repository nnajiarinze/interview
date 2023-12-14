package com.paf.exercise.controller;

import com.paf.exercise.datasource.provider.database.model.DbPrizes;
import com.paf.exercise.model.Prize;
import com.paf.exercise.model.reponse.PrizesResponseBuilder;
import com.paf.exercise.model.request.PostReqPrize;
import com.paf.exercise.service.PrizesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/prizes")
public class PrizesController {

  private final PrizesResponseBuilder prizesResponseBuilder;

  private PrizesService prizesService;

  public PrizesController(PrizesResponseBuilder prizesResponseBuilder, PrizesService prizesService) {
    this.prizesResponseBuilder = prizesResponseBuilder;
    this.prizesService = prizesService;
  }


  @GetMapping("/{id}")
  public ResponseEntity<Prize> getPrizeById(@PathVariable Long id) {
    DbPrizes dbPrize = prizesService.getPrizeById(id);
    return ResponseEntity.ok().body(prizesResponseBuilder.build(dbPrize));
  }

  @PostMapping("")
  public ResponseEntity<Prize> createPrize(@RequestBody PostReqPrize postReqPrize) {
    DbPrizes dbPrize = prizesService.createPrize(postReqPrize);
    return ResponseEntity.ok().body(prizesResponseBuilder.build(dbPrize));
  }

}
