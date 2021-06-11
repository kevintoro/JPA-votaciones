package com.ufps.web.EleccionesElectronicasJPA.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionDB {
  private final EntityManager entityManager;
  private static ConnectionDB connectionDB;
  public ConnectionDB() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("votaciones");
    entityManager = emf.createEntityManager();
  }
  
  public EntityManager getEm() {
    return entityManager;
  }
  
  public static ConnectionDB getConnectionDB() {
    if (connectionDB == null) {
      connectionDB = new ConnectionDB();
    }
    return connectionDB;
  }
}
