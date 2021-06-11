<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <c:if test="${votante != null}">
    <title>Actualizar Votante</title>
  </c:if>
  <c:if test="${votante == null}">
    <title>Agregar Votante</title>
  </c:if>
  <style>
    <%@include file="styles/bootstrap.min.css" %>
  </style>
</head>
<body class="container mt-5">
<div class="col-11 col-sm-9 col-md-7 col-lg-6 mx-auto">
  <div class="card">
    <div class="card-body">
      <c:if test="${votante != null}">
        <h3 class="card-title">Actualizar información de votante</h3>
      </c:if>
      <c:if test="${votante == null}">
        <h3 class="card-title">Registro de población electoral</h3>
      </c:if>
      <c:if test="${votante != null}">
      <form action="edit" method="post">
        </c:if>
        <c:if test="${votante == null}">
        <form action="add" method="post">
          </c:if>
          <div class="form-group">
            <label for="estamento">Estamento: </label>
            <select name="estamento" id="estamento" class="form-control" required>
              <c:forEach var="estamento" items="${estamentos}">
                <option value="${estamento.id}">
                  <c:out value="${estamento.descripcion}"/>
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="tipo-documento">Tipo de documento:</label>
            <select name="tipo-documento" id="tipo-documento" class="form-control" required>
              <c:forEach var="tipoDocumento" items="${tipoDocumentos}">
                <option value="${tipoDocumento.id}">
                  <c:out value="${tipoDocumento.descripcion}"/>
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="documento">Documento:</label>
            <c:if test="${votante != null}">
              <input type="number" class="form-control" required id="documento" name="documento"
                     value="<c:out value="${votante.documento}"/>">
            </c:if>
            <c:if test="${votante == null}">
              <input type="number" class="form-control" required id="documento" name="documento">
            </c:if>
          </div>
          <div class="form-group">
            <label for="nombre">Nombre:</label>
            <c:if test="${votante != null}">
              <input type="text" class="form-control" required id="nombre" name="nombre"
                     value="<c:out value='${votante.nombre}'/>">
            </c:if>
            <c:if test="${votante == null}">
              <input type="text" class="form-control" required id="nombre" name="nombre">
            </c:if>
          
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <c:if test="${votante != null}">
              <input type="email" class="form-control" required id="email" name="email"
                     value="<c:out value="${votante.email}"/>">
            </c:if>
            <c:if test="${votante == null}">
              <input type="email" class="form-control" required id="email" name="email">
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
          <div class="d-grid mt-3">
            <c:if test="${votante != null}">
              <button type="submit" class="btn btn-danger btn-lg p-3">Actualizar Votante</button>
            </c:if>
            <c:if test="${votante == null}">
              <button type="submit" class="btn btn-danger btn-lg p-3">Registrar Votante</button>
            </c:if>
          </div>
          <c:if test="${votante != null}">
            <input type="hidden" name="votante_id" value="<c:out value='${votante.id}' />"/>
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

