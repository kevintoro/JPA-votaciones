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
@Table(name = "eleccion")
public class Eleccion implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "nombre")
  private String nombre;
  
  @Column(name = "fechainicio")
  private Date echainicio;
  
  @Column(name = "fechafin")
  private Date echafin;
  
  @Column(name = "cargo")
  private String cargo;
  
}
