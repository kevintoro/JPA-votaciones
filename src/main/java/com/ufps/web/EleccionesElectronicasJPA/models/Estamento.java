package com.ufps.web.EleccionesElectronicasJPA.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Getter
@Setter
@Entity
@Table(name = "estamento")
public class Estamento implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "eleccion")
  private Long eleccion;
  
  @Column(name = "descripcion")
  private String descripcion;
  
}
