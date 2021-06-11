package com.ufps.web.EleccionesElectronicasJPA.DAO;

import com.ufps.web.EleccionesElectronicasJPA.models.Votante;
import com.ufps.web.EleccionesElectronicasJPA.utils.ConnectionDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VotanteDAO {
  private final ConnectionDB connectionDB;
  
  public VotanteDAO() {
    connectionDB = ConnectionDB.getConnectionDB();
  }
  
  public void insertVotante(Votante votante) {
    EntityManager manager = connectionDB.getEm();
    manager.getTransaction().begin();
    manager.persist(votante);
    manager.getTransaction().commit();
  }
  
  @SuppressWarnings("unchecked")
  public List<Votante> getAllVotantes() {
    EntityManager manager = connectionDB.getEm();
    Query query = manager.createQuery("select v from Votante v");
    return (List<Votante>) query.getResultList();
  }
  
  public void removeVotante(Long id) {
    Votante votante = getSingleVotante(id);
    if (votante != null) {
      EntityManager manager = connectionDB.getEm();
      manager.getTransaction().begin();
      manager.remove(votante);
      manager.getTransaction().commit();
    }
  }
  
  public Votante getSingleVotante(Long id) {
    return connectionDB.getEm().find(Votante.class, id);
  }
  
  public void updateVotante(Votante votante) {
    EntityManager manager = connectionDB.getEm();
    manager.getTransaction().begin();
    manager.merge(votante);
    manager.getTransaction().commit();
  }
}
