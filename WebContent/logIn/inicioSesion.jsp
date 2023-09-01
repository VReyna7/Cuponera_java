<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope }">
<!DOCTYPE html>
<html>
<head>
	<title>Inicio de Sesión</title>
	<jsp:include page="/Cabezera.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/iniciosesion.css">
<body>
<body>
	<!-- As a heading -->
            <nav class="navbar navbar-dark bg-dark justify-content-between">
                <span class="navbar-brand mb-0 h1 cuponera_title">LA CUPONERA</span>
                <div>
                    <button class="inicio_sesion_button">Iniciar sesión</button>
                    <button class="registrarse_button">Registrarse</button>
                </div>
        </nav>
    <!--Fin navbar -->

    <main>
        <article class="main_article">
            <div class="left_container">
            </div>
            <div class="right_container">
                    <h2 class="iniciosesion_titulo">Inicio de sesión</h2>
                    <form role="form" action="session.do?op=ingresar" method="POST">
                    	<input type="hidden" name="op" value="ingresar">
                        <div class="form-group">
                          <label for="exampleInputEmail1">Email address</label>
                          <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                          <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                          <label for="exampleInputPassword1">Password</label>
                          <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
                        </div>
                        <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="exampleCheck1">
                          <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                      </form>
                      <c:if test="${not empty listaErrores}">
					 <div class="alert alert-danger">
						 <ul>
							 <c:forEach var="errores" items="${requestScope.listaErrores}">
							 	<li>${errores}</li>
							 </c:forEach>
						 </ul>
					 </div>
					</c:if>
            </div>
        </article>
    </main>
    <footer class="footer">
      <h6>© 2023 Cuponera Derechos reservados</h6>
  </footer>
</body>
</html>
</c:if>