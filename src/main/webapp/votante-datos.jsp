<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Confirmar Datos</title>
  <style>
    <%@include file="styles/bootstrap.min.css" %>
  </style>
</head>
<body class="container">
<c:if test="${error != null}">
  <div class="col-6 mt-5 mx-auto">
    <div class="card p-5">
      <h3 class="card-title text-danger text-center">${error}</h3>
    </div>
  </div>
</c:if>
<c:if test="${error == null}">
  <div class="col-11 col-sm-9 col-md-8 col-lg-7 mx-auto mt-5">
    <div class="card">
      <div class="card-body">
        <h3 class="card-title">Validación del proceso de voto</h3>
        <form action="validate" method="post">
          <div>
            <label for="nombre">Nombre: </label>
            <input type="text" disabled name="nombre" id="nombre" value="<c:out value="${votante.nombre}"/>"
                   class="form-control">
          </div>
          <div>
            <label for="email">Email:</label>
            <input type="email" disabled name="email" id="email" value="<c:out value="${votante.email}" />"
                   class="form-control">
          </div>
          <div>
            <label for="tipoDocumento">Tipo de documento:</label>
            <select name="tipoDocumento" id="tipoDocumento" class="form-control" required>
              <c:forEach items="${tipoDocumentos}" var="tipoDocumento">
                <option value="${tipoDocumento.id}">${tipoDocumento.descripcion}</option>
              </c:forEach>
            </select>
          </div>
          <div>
            <label for="estamento">Estamento:</label>
            <select name="estamento_id" id="estamento" class="form-control" required>
              <c:forEach items="${estamentos}" var="estamento">
                <option value="${estamento.id}">${estamento.descripcion}</option>
              </c:forEach>
            </select>
          </div>
          <div>
            <label for="documento">Documento:</label>
            <input type="number" name="documento" id="documento" class="form-control" required>
          </div>
          <div>
            <input type="hidden" id="votante_id" name="votante_id" class="form-control"
                   value="<c:out value="${votante.id}" />">
          </div>
          <div class="d-grid mt-3">
            <button type="submit" class="btn btn-danger btn-lg p-3">Confirmo mis Datos</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
