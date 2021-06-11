<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Votantes Elecciones UFPS</title>
  <style>
    <%@include file="styles/bootstrap.min.css" %>
  </style>
</head>
<body class="container mt-4">
<h1>Listado de votantes</h1>
<hr>
<div class="container text-left">
  
  <a href="<%=request.getContextPath()%>/add" class="btn btn-outline-danger">
    Agregar Votante
  </a>
</div>
<br>
<table class="table table-bordered">
  <thead>
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Email</th>
    <th>Documento</th>
    <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <!--   for (Todo todo: todos) {  -->
  <c:forEach var="votante" items="${votantes}">
    <tr>
      <td>
        <c:out value="${votante.id}"/>
      </td>
      <td>
        <c:out value="${votante.nombre}"/>
      </td>
      <td>
        <c:out value="${votante.email}"/>
      </td>
      <td>
        <c:out value="${votante.documento}"/>
      </td>
      <td>
        <a href="edit?votante_id=<c:out value='${votante.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="delete?votante_id=<c:out value='${votante.id}' />">Delete</a>
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