package com.ufps.web.EleccionesElectronicasJPA.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "votante")
public class Votante implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "nombre")
  private String nombre;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "documento")
  private String documento;
  
  @Column(name = "tipodocumento")
  private Long tipodocumento;
  
  @Column(name = "eleccion")
  private Long eleccion;
  
}
