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
import lombok.Setter;


@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Table(name = "TOURNAMENTS")
public class DbTournaments {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOURNAMENTS_ID_SEQ")
  @SequenceGenerator(name = "TOURNAMENTS_ID_SEQ", sequenceName = "TOURNAMENTS_ID_SEQ", allocationSize = 1)
  @Column(name = "ID")
  private Long id;
  private String name;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "currencyId", referencedColumnName = "id")
  private DbCurrencies currency;

}
