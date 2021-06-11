package com.ufps.web.EleccionesElectronicasJPA.DAO;

import com.ufps.web.EleccionesElectronicasJPA.models.Eleccion;
import com.ufps.web.EleccionesElectronicasJPA.utils.ConnectionDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EleccionDAO {
  private final ConnectionDB connectionDB;
  public EleccionDAO() {
    connectionDB = ConnectionDB.getConnectionDB();
  }
  
  @SuppressWarnings("unchecked")
  public List<Eleccion> getAllElecciones() {
    EntityManager manager = connectionDB.getEm();
    Query query = manager.createQuery("select e from Eleccion e");
    return (List<Eleccion>) query.getResultList();
  }
}
