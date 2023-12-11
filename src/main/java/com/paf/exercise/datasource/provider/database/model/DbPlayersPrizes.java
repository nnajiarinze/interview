package com.paf.exercise.datasource.provider.database.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "PLAYERS_PRIZES")
public class DbPlayersPrizes {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYERS_PRIZES_ID_SEQ")
  @SequenceGenerator(name = "PLAYERS_PRIZES_ID_SEQ", sequenceName = "PLAYERS_PRIZES_ID_SEQ", allocationSize = 1)
  @Column(name = "ID")
  private Long id;
  private Long prizeId;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "playerId", referencedColumnName = "id")
  private DbPlayers player;


}
