package com.ufps.web.EleccionesElectronicasJPA.controller;

import com.ufps.web.EleccionesElectronicasJPA.DAO.*;
import com.ufps.web.EleccionesElectronicasJPA.models.*;
import com.ufps.web.EleccionesElectronicasJPA.utils.EmailSend;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "VotanteServlet", urlPatterns = {"/"})
public class VotanteServlet extends HttpServlet {
  private VotanteDAO votanteDAO;
  private EstamentoDAO estamentoDAO;
  private TipoDocumentoDAO tipoDocumentoDAO;
  private EleccionDAO eleccionDAO;
  private CandidatoDAO candidatoDAO;
  private VotoDAO votoDAO;
  private EmailSend emailSend;
  @Override
  public void init() throws ServletException {
    super.init();
    votanteDAO = new VotanteDAO();
    estamentoDAO = new EstamentoDAO();
    tipoDocumentoDAO = new TipoDocumentoDAO();
    eleccionDAO = new EleccionDAO();
    candidatoDAO = new CandidatoDAO();
    votoDAO = new VotoDAO();
    emailSend = new EmailSend();
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final String action = request.getServletPath();
    switch (action) {
      case "/add":
        addVotante(request, response);
        break;
      case "/delete":
        deleteVotante(request, response);
        break;
      case "/edit":
        editVotante(request, response);
        break;
      case "/validate":
        showValidationInfo(request, response);
        break;
      case "/test":
        test(response);
        break;
      default:
        showTableInfoVotantes(request, response);
        break;
    }
  }
  
  private void showValidationInfo(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String email = request.getParameter("email");
    if (email != null && !email.isEmpty()) {
      Votante votante = null;
      List<Votante> votantes = votanteDAO.getAllVotantes();
      for(Votante vt: votantes) {
        if (vt.getEmail().equalsIgnoreCase(email)) {
          votante = vt;
          break;
        }
      }
      if (votante != null) {
        List<Tipodocumento> tipoDocumentos = tipoDocumentoDAO.getAllTipoDocumentos();
        List<Estamento> estamentos = estamentoDAO.getAllEstamentos();
        request.setAttribute("estamentos", estamentos);
        request.setAttribute("tipoDocumentos", tipoDocumentos);
        request.setAttribute("votante", votante);
      } else {
        String error = "No se encontró al usuario para este enlace";
        request.setAttribute("error", error);
      }
    } else {
      String error = "Enlace no válido";
      request.setAttribute("error", error);
    }
    RequestDispatcher rd = request.getRequestDispatcher("votante-datos.jsp");
    rd.forward(request, response);
  }
  
  private void editVotante(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    loadRequestAttributes(request);
    Long votanteId = Long.parseLong(request.getParameter("votante_id"));
    Votante votante = votanteDAO.getSingleVotante(votanteId);
    request.setAttribute("votante", votante);
    RequestDispatcher rd = request.getRequestDispatcher("votante-form.jsp");
    rd.forward(request, response);
  }
  
  private void showTableInfoVotantes(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Votante> votantes = votanteDAO.getAllVotantes();
    request.setAttribute("votantes", votantes);
    RequestDispatcher rd = request.getRequestDispatcher("votante-list.jsp");
    rd.forward(request, response);
  }
  
  private void deleteVotante(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Long votanteId = Long.parseLong(request.getParameter("votante_id"));
    votanteDAO.removeVotante(votanteId);
    response.sendRedirect("list");
  }
  
  private void addVotante(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    loadRequestAttributes(request);
    RequestDispatcher rd = request.getRequestDispatcher("votante-form.jsp");
    rd.forward(request, response);
  }
  
  private void loadRequestAttributes(HttpServletRequest request) {
    List<Estamento> estamentos = estamentoDAO.getAllEstamentos();
    request.setAttribute("estamentos", estamentos);
    List<Tipodocumento> tipoDocumentos = tipoDocumentoDAO.getAllTipoDocumentos();
    request.setAttribute("tipoDocumentos", tipoDocumentos);
    List<Eleccion> elecciones = eleccionDAO.getAllElecciones();
    request.setAttribute("elecciones", elecciones);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String action = request.getServletPath();
    switch (action) {
      case "/add":
        insertVotante(request, response);
        break;
      case "/edit":
        updateVotante(request, response);
        break;
      case "/validate":
        validateInfo(request, response);
        break;
      case "/vote":
        createVote(request, response);
        break;
    }
  }
  
  private void createVote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    List<Voto> votos = votoDAO.getAllVotos();
    long votanteId = Long.parseLong(request.getParameter("votante_id").trim());
    long candidatoId = Long.parseLong(request.getParameter("candidato_id").trim());
    long estamentoId = Long.parseLong(request.getParameter("estamento_id").trim());
    boolean existe = false;
    for (Voto vt: votos) {
      if(vt.getVotante() == votanteId && vt.getEstamento() == estamentoId) {
        existe = true;
        break;
      }
    }
    if(existe) {
      String error = "Ya se realizó el voto de este usuario con el estamento dado";
      request.setAttribute("error", error);
      RequestDispatcher rd = request.getRequestDispatcher("votante-datos.jsp");
      rd.forward(request, response);
    } else {
      Voto voto = new Voto();
      voto.setVotante(votanteId);
      voto.setFechaVoto(new Date());
      voto.setFechaCreacion(new Date());
      voto.setCandidato(candidatoId);
      voto.setEstamento(estamentoId);
      voto.setUuid("VTCNES*546589jkghkasLKJ7534UFPS-VT-" + votanteId);
      voto.setEnlace(request.getRequestURI());
      votoDAO.insertVoto(voto);
      response.sendRedirect("list");
    }
  }
  
  private void validateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String votanteId = request.getParameter("votante_id");
    String estamento = request.getParameter("estamento_id");
    List<Candidato> candidatos = candidatoDAO.getAllCandidatos();
    request.setAttribute("candidatos", candidatos);
    request.setAttribute("votante_id", votanteId);
    request.setAttribute("estamento_id", estamento);
    RequestDispatcher rd = request.getRequestDispatcher("candidatos-voto.jsp");
    rd.forward(request, response);
  }
  
  private void updateVotante(HttpServletRequest request, HttpServletResponse response) throws IOException {
    votanteDAO.updateVotante(manageVotante(request, true));
    response.sendRedirect("list");
  }
  
  private void insertVotante(HttpServletRequest request, HttpServletResponse response) throws IOException {
    votanteDAO.insertVotante(manageVotante(request, false));
    response.sendRedirect("list");
  }
  
  private Votante manageVotante(HttpServletRequest request, boolean update) {
    Votante votante = new Votante();
    String nombre = request.getParameter("nombre");
    String email = request.getParameter("email");
    String documento = request.getParameter("documento");
    long tipoDocumento = Long.parseLong(request.getParameter("tipo-documento"));
    long estamento = Long.parseLong(request.getParameter("estamento"));
    Estamento es = estamentoDAO.getSingleEstamento(estamento);
    if (update) {
      long votanteId = Long.parseLong(request.getParameter("votante_id"));
      votante.setId(votanteId);
    } else {
      String message = "http://localhost:8080/EleccionesElectronicasJPA_war_exploded/validate?email="+email;
      emailSend.sendMessage("Enlace de votacion", message, email);
    }
    votante.setNombre(nombre);
    votante.setTipodocumento(tipoDocumento);
    votante.setEmail(email);
    votante.setDocumento(documento);
    votante.setEleccion(es.getEleccion());
    return votante;
  }
  
  private void test(HttpServletResponse response) throws IOException {
    emailSend.sendMessage("Test","Hello World", "ricib60878@jmpant.com");
    response.sendRedirect("list");
  }
}
