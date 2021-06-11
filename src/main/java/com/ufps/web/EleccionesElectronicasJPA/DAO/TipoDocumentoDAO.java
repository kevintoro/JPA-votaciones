package com.ufps.web.EleccionesElectronicasJPA.DAO;

import com.ufps.web.EleccionesElectronicasJPA.models.Tipodocumento;
import com.ufps.web.EleccionesElectronicasJPA.utils.ConnectionDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TipoDocumentoDAO {
  private final ConnectionDB connectionDB;
  public TipoDocumentoDAO() {
    connectionDB = ConnectionDB.getConnectionDB();
  }
  
  @SuppressWarnings("unchecked")
  public List<Tipodocumento> getAllTipoDocumentos() {
    EntityManager manager = connectionDB.getEm();
    Query query = manager.createQuery("select t from Tipodocumento t");
    return (List<Tipodocumento>) query.getResultList();
  }
}
