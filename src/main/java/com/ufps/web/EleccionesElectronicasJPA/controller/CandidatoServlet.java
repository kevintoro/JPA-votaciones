package com.ufps.web.EleccionesElectronicasJPA.controller;

import com.ufps.web.EleccionesElectronicasJPA.DAO.CandidatoDAO;
import com.ufps.web.EleccionesElectronicasJPA.DAO.EleccionDAO;
import com.ufps.web.EleccionesElectronicasJPA.models.Candidato;
import com.ufps.web.EleccionesElectronicasJPA.models.Eleccion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CandidatoServlet", urlPatterns = {"/candidato", "/candidato/*"})
public class CandidatoServlet extends HttpServlet {
  private CandidatoDAO candidatoDAO;
  private EleccionDAO eleccionDAO;
  
  @Override
  public void init() throws ServletException {
    super.init();
    candidatoDAO = new CandidatoDAO();
    eleccionDAO = new EleccionDAO();
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String act = request.getRequestURI();
    String[] opt = act.split("/");
    String action = "list";
    if(opt.length==4) {
      action = "/"+opt[3];
    }
    System.out.println(action);
    switch (action) {
      case "/add":
        createCandidato(request, response);
        break;
      case "/edit":
        editCandidato(request, response);
        break;
      case "/delete":
        removeCandidato(request, response);
        break;
      default:
        showCandidatoList(request, response);
        break;
    }
  }
  
  private void removeCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException {
    long candidatoId = Long.parseLong(request.getParameter("candidato_id"));
    candidatoDAO.removeCandidato(candidatoId);
    response.sendRedirect("candidato/list");
  }
  
  private void createCandidato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Eleccion> elecciones = eleccionDAO.getAllElecciones();
    request.setAttribute("elecciones", elecciones);
    RequestDispatcher rd = request.getRequestDispatcher("/candidato-form.jsp");
    rd.forward(request, response);
  }
  
  private void editCandidato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long candidatoId = Long.parseLong(request.getParameter("candidato_id"));
    Candidato candidato = candidatoDAO.getSingleCandidato(candidatoId);
    request.setAttribute("candidato", candidato);
    List<Eleccion> elecciones = eleccionDAO.getAllElecciones();
    request.setAttribute("elecciones", elecciones);
    RequestDispatcher rd = request.getRequestDispatcher("/candidato-form.jsp");
    rd.forward(request, response);
  }
  
  private void showCandidatoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Candidato> candidatos = candidatoDAO.getAllCandidatos();
    request.setAttribute("candidatos", candidatos);
    RequestDispatcher rd = request.getRequestDispatcher("/candidato-list.jsp");
    rd.forward(request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String action = request.getServletPath();
    switch (action) {
      case "/add":
        insertNewCandidato(request, response);
        break;
      case "/edit":
        updateCandidato(request, response);
        break;
    }
  }
  
  private void updateCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Candidato candidato = manageCandidato(request, true);
    candidatoDAO.updateCandidato(candidato);
    response.sendRedirect("/list");
  }
  
  private void insertNewCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Candidato candidato = manageCandidato(request, false);
    List<Candidato> candidatoList = candidatoDAO.getAllCandidatos();
    boolean existe = false;
    for (Candidato cd : candidatoList) {
      if (cd.getDocumento().equals(candidato.getDocumento())) {
        existe = true;
        break;
      }
    }
    if(!existe) {
      candidatoDAO.insertCandidato(candidato);
      response.sendRedirect("list");
    } else {
      String error = "Cédula ya registrada";
      request.setAttribute("error", error);
      RequestDispatcher rd = request.getRequestDispatcher("/candidato-form.jsp");
      rd.forward(request, response);
    }
  }
  
  private Candidato manageCandidato(HttpServletRequest request, boolean update) {
    Candidato candidato = new Candidato();
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String documento = request.getParameter("documento");
    long eleccion = Long.parseLong(request.getParameter("proceso"));
    candidato.setNombre(nombre);
    candidato.setApellido(apellido);
    candidato.setDocumento(documento);
    candidato.setEleccion(eleccion);
    if (update) {
      long candidatoId = Long.parseLong(request.getParameter("candidato_id"));
      candidato.setId(candidatoId);
      int numero = Integer.parseInt(request.getParameter("numero"));
      candidato.setNumero(numero);
    } else {
      candidato.setNumero(candidatoDAO.getAllCandidatos().size() + 1);
    }
    
    return candidato;
  }
}
