<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <c:if test="${candidato != null}">
    <title>Actualizar Candidato</title>
  </c:if>
  <c:if test="${candidato == null}">
    <title>Agregar Candidato</title>
  </c:if>
  <style>
    <%@include file="styles/bootstrap.min.css" %>
  </style>
</head>
<body class="container mt-5">
<c:if test="${error != null}">
  <div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong>Holy guacamole! </strong><c:out value="${error}"/>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  </div>
</c:if>
<div class="col-11 col-sm-9 col-md-7 col-lg-6 mx-auto">
  <div class="card">
    <div class="card-body">
      <c:if test="${candidato != null}">
        <h3 class="card-title">Actualizar información De Candidato</h3>
      </c:if>
      <c:if test="${candidato == null}">
        <h3 class="card-title">Registro De Candidato</h3>
      </c:if>
      <c:if test="${candidato != null}">
      <form action="edit" method="post">
        </c:if>
        <c:if test="${candidato == null}">
        <form action="add" method="post">
          </c:if>
          <div class="form-group">
            <label for="documento">Documento:</label>
            <c:if test="${candidato != null}">
              <input type="number" class="form-control" required id="documento" name="documento"
                     value="<c:out value="${candidato.documento}"/>">
            </c:if>
            <c:if test="${candidato == null}">
              <input type="number" class="form-control" required id="documento" name="documento">
            </c:if>
          </div>
          <div class="form-group">
            <label for="nombre">Nombre:</label>
            <c:if test="${candidato != null}">
              <input type="text" class="form-control" required id="nombre" name="nombre"
                     value="<c:out value='${candidato.nombre}'/>">
            </c:if>
            <c:if test="${candidato == null}">
              <input type="text" class="form-control" required id="nombre" name="nombre">
            </c:if>
          </div>
          <div class="form-group">
            <label for="apellido">Apellido:</label>
            <c:if test="${candidato != null}">
              <input type="text" class="form-control" required id="apellido" name="apellido"
                     value="<c:out value='${candidato.apellido}'/>">
            </c:if>
            <c:if test="${candidato == null}">
              <input type="text" class="form-control" required id="apellido" name="apellido">
            </c:if>
          </div>
          <div class="form-group">
            <label for="proceso">Proceso:</label>
            <select name="proceso" id="proceso" class="form-control" required>
              <c:forEach var="eleccion" items="${elecciones}">
                <option value="${eleccion.id}">
                  <c:out value="${eleccion.nombre}"/>
                </option>
              </c:forEach>
            </select>
          </div>
          <c:if test="${candidato != null}">
            <input type="hidden" name="numero" value="<c:out value='${candidato.numero}' />" id="numero"/>
          </c:if>
          <div class="d-grid mt-3">
            <c:if test="${candidato != null}">
              <button type="submit" class="btn btn-danger btn-lg p-3">Actualizar Candidato</button>
            </c:if>
            <c:if test="${candidato == null}">
              <button type="submit" class="btn btn-danger btn-lg p-3">Registrar Candidato</button>
            </c:if>
          </div>
          <c:if test="${candidato != null}">
            <input type="hidden" name="candidato_id" value="<c:out value='${candidato.id}' />"/>
          </c:if>
        </form>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>

