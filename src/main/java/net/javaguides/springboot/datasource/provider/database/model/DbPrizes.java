package net.javaguides.springboot.datasource.provider.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "PRIZES")
public class DbPrizes {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIZES_ID_SEQ")
  @SequenceGenerator(name = "PRIZES_ID_SEQ", sequenceName = "PRIZES_ID_SEQ", allocationSize = 1)
  @Column(name = "ID")
  private Long id;
  private String name;
  private Long amount;
  @Column(name = "tournament_id")
  private Long tournamentId;

}
