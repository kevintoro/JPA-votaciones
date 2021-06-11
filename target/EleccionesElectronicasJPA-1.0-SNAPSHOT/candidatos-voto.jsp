<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Candidatos</title>
  <style>
    <%@include file="styles/bootstrap.min.css" %>
  </style>
</head>
<body class="container">
<div class="col-11 col-sm-10 col-md-9 col-lg-8 mx-auto mt-5">
  <form action="vote" method="post">
    <input type="hidden" value="<c:out value="${estamento_id}"/>" name="estamento_id">
    <input type="hidden" value="<c:out value="${votante_id}"/> " name="votante_id">
    <div class="row row-cols-1 row-cols-md-2 g-4">
      <c:forEach var="candidato" items="${candidatos}">
        <div class="col">
          <div class="card">
            <img src="https://www.sideralsoft.com/wp-content/uploads/2019/03/user-placeholder.png" class="card-img-top"
                 alt="user_placeholder">
            <div class="card-body">
              <h5 class="card-title text-danger fw-bolder">Candidato #${candidato.numero}</h5>
              <input type="radio" name="candidato_id" id="${candidato.id}" class="form-check-input"
                     value="<c:out value="${candidato.id}"/>">
              <label for="${candidato.id}">${candidato.nombre} ${candidato.apellido}</label>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
    <div class="d-grid my-3">
      <button type="submit" class="btn btn-danger btn-lg p-3">Confirmo mi voto</button>
    </div>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
