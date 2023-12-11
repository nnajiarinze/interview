package net.javaguides.springboot.controller;

import net.javaguides.springboot.datasource.provider.database.model.DbPlayers;
import net.javaguides.springboot.datasource.provider.database.model.DbPrizes;
import net.javaguides.springboot.model.Player;
import net.javaguides.springboot.model.Prize;
import net.javaguides.springboot.model.reponse.PrizesResponseBuilder;
import net.javaguides.springboot.model.request.PostReqPlayer;
import net.javaguides.springboot.model.request.PostReqPrize;
import net.javaguides.springboot.service.PrizesService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private PrizesService prizesService;

  public PrizesController(PrizesResponseBuilder prizesResponseBuilder) {
    this.prizesResponseBuilder = prizesResponseBuilder;
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
