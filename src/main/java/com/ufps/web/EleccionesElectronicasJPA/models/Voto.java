package com.ufps.web.EleccionesElectronicasJPA.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * $table.getTableComment()
 */
@Getter
@Setter
@Entity
@Table(name = "voto")
public class Voto implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "fechacreacion", nullable = false)
  private Date fechaCreacion;
  
  @Column(name = "fechavoto")
  private Date fechaVoto;
  
  @Column(name = "uuid")
  private String uuid;
  
  @Column(name = "enlace")
  private String enlace;
  
  @Column(name = "estamento")
  private Long estamento;
  
  @Column(name = "candidato")
  private Long candidato;
  
  @Column(name = "votante")
  private Long votante;
  
}
