package com.ufps.web.EleccionesElectronicasJPA.DAO;

import com.ufps.web.EleccionesElectronicasJPA.models.Voto;
import com.ufps.web.EleccionesElectronicasJPA.utils.ConnectionDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VotoDAO {
  private final ConnectionDB connectionDB;
  public VotoDAO() {
    connectionDB = ConnectionDB.getConnectionDB();
  }
  
  @SuppressWarnings("unchecked")
  public List<Voto> getAllVotos() {
    EntityManager manager = connectionDB.getEm();
    Query query = manager.createQuery("select v from Voto v");
    return (List<Voto>) query.getResultList();
  }
  
  public void insertVoto(Voto voto) {
    EntityManager manager = connectionDB.getEm();
    manager.getTransaction().begin();
    manager.persist(voto);
    manager.getTransaction().commit();
  }
}
