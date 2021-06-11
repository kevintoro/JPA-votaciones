<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Candidatos Elecciones UFPS</title>
  <style>
    <%@include file="styles/bootstrap.min.css" %>
  </style>
</head>
<body class="container mt-4">
<h1>Listado de Candidatos</h1>
<hr>
<div class="container text-left">
  
  <a href="<%=request.getContextPath()%>/candidato/add" class="btn btn-outline-danger">
    Agregar Candidato
  </a>
</div>
<br>
<table class="table table-bordered">
  <thead>
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Numero</th>
    <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <!--   for (Todo todo: todos) {  -->
  <c:forEach var="candidato" items="${candidatos}">
    <tr>
      <td>
        <c:out value="${candidato.id}"/>
      </td>
      <td>
        <c:out value="${candidato.nombre}"/>
      </td>
      <td>
        <c:out value="${candidato.apellido}"/>
      </td>
      <td>
        <c:out value="${candidato.numero}"/>
      </td>
      <td>
        <a href="<%=request.getContextPath()%>/candidato/edit?candidato_id=<c:out value='${candidato.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/candidato/delete?candidato_id=<c:out value='${candidato.id}' />">Delete</a>
      </td>
    </tr>
  </c:forEach>
  <!-- } -->
  </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>