package com.ufps.web.EleccionesElectronicasJPA.DAO;

import com.ufps.web.EleccionesElectronicasJPA.models.Candidato;
import com.ufps.web.EleccionesElectronicasJPA.utils.ConnectionDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Clase que interactua con la BD y los objetos de tipo Candidato
 *
 * @see Candidato
 */
public class CandidatoDAO {
  private final ConnectionDB connectionDB;
  
  public CandidatoDAO() {
    connectionDB = ConnectionDB.getConnectionDB();
  }
  
  /**
   * Obtiene la lista de todos los candidatos guardados en la BD
   *
   * @return Lista de objetos de tipo Candidato con todos los candidatos guardados
   * @see Candidato
   */
  @SuppressWarnings("unchecked")
  public List<Candidato> getAllCandidatos() {
    EntityManager manager = connectionDB.getEm();
    Query query = manager.createQuery("select c from Candidato c");
    return (List<Candidato>) query.getResultList();
  }
  
  /**
   * Agrega un candidato nuevo a la BD
   *
   * @param candidato Objeto de tipo Candidato que se agregará a la BD
   * @see Candidato
   */
  public void insertCandidato(Candidato candidato) {
    EntityManager manager = connectionDB.getEm();
    manager.getTransaction().begin();
    manager.persist(candidato);
    manager.getTransaction().commit();
  }
  
  /**
   * Busca un candidato en la BD con el id
   *
   * @param id dato de tipo Long con el id del candidato
   * @return Objeto de tipo Candidato si se encuentra, caso contrario, null
   * @see Candidato
   */
  public Candidato getSingleCandidato(Long id) {
    return connectionDB.getEm().find(Candidato.class, id);
  }
  
  /**
   * Elimina un candidato de la BD
   *
   * @param id valor de tipo Long con el id del candidato a eliminar
   */
  public void removeCandidato(Long id) {
    EntityManager manager = connectionDB.getEm();
    Candidato candidato = getSingleCandidato(id);
    if (candidato != null) {
      manager.getTransaction().begin();
      manager.remove(candidato);
      manager.getTransaction().commit();
    }
  }
  
  /**
   * Actualiza el candidato en la BD
   *
   * @param candidato Objeto de tipo Candidato con la información a actualizar
   * @see Candidato
   */
  public void updateCandidato(Candidato candidato) {
    EntityManager manager = connectionDB.getEm();
    manager.getTransaction().begin();
    manager.merge(candidato);
    manager.getTransaction().commit();
  }
}
