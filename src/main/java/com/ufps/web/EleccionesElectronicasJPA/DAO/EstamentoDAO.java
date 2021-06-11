package com.ufps.web.EleccionesElectronicasJPA.DAO;

import com.ufps.web.EleccionesElectronicasJPA.models.Estamento;
import com.ufps.web.EleccionesElectronicasJPA.utils.ConnectionDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EstamentoDAO {
  private final ConnectionDB connectionDB;
  
  public EstamentoDAO() {
    connectionDB = ConnectionDB.getConnectionDB();
  }
  
  @SuppressWarnings("unchecked")
  public List<Estamento> getAllEstamentos() {
    EntityManager manager = connectionDB.getEm();
    Query query = manager.createQuery("select e from Estamento e");
    return (List<Estamento>) query.getResultList();
  }
  
  public Estamento getSingleEstamento(Long id) {
    return connectionDB.getEm().find(Estamento.class, id);
  }
}
