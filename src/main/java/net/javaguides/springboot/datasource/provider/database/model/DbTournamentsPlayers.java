package net.javaguides.springboot.datasource.provider.database.model;

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
@Table(name = "TOURNAMENTS_PLAYERS")
public class DbTournamentsPlayers {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOURNAMENTS_PLAYERS_ID_SEQ")
  @SequenceGenerator(name = "TOURNAMENTS_PLAYERS_ID_SEQ", sequenceName = "TOURNAMENTS_PLAYERS_ID_SEQ", allocationSize = 1)
  @Column(name = "ID")
  private Long id;
  private Long tournamentId;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "playerId", referencedColumnName = "id")
  private DbPlayers player;

}
