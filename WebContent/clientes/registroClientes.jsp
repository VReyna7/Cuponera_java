<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Registro</title>
	<jsp:include page="/Cabezera.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/registro.css">
    
<body>
    <nav class="navbar navbar-dark bg-dark justify-content-between">
            <span class="navbar-brand mb-0 h1 cuponera_title">LA CUPONERA</span>
            <div>
                <a href="${pageContext.request.contextPath}/session.do?op=login"><button class="inicio_sesion_button">Iniciar sesión</button></a>
               <a href="${pageContext.request.contextPath}/clientes.do?op=nuevo"> <button class="registrarse_button">Registrarse</button></a>
            </div>
        </nav>
        <!--Fin navbar -->


    <main class="main_container">
        <article class="main_article">
            <div class="left_container">
            </div>
            <div class="right_container">
                    <h2 class="registro_titulo">Registrate para comprar los mejores cupones</h2>
                    <c:if test="${not empty listaErrores}">
					 <div class="alert alert-danger">
						 <ul>
						 <c:forEach var="errores" items="${requestScope.listaErrores}">
						 <li>${errores}</li>
						 </c:forEach>
						 </ul>
					 </div>
					</c:if>
                    <form role="form" action="clientes.do" method="POST">
                    	<input type="hidden" name="op" value="insertar">
                        <div class="form_spaces1">
                            <div class="form-group">                  
                                <label>Nombres</label>
                                <input type="text" name="nombre" id="nombre" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Apellidos</label>
                                <input type="text" name="apellido" id="apellido" class="form-control" required>
                            </div>
                        </div>

                        <div class="form_spaces1">
                            <div class="form-group">
                                <label>Telefono</label>
                                <input type="tel" name="telefono" id="telefono" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>DUI</label>
                                <input type="tel" name="dui" id="dui" class="form-control" required>
                            </div>
                            
                        </div>
                        
                        
                        <div class="form-group">
                              <label>Dirección</label>
                              <input type="tel" name="direccion" id="direccion" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email">
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                          </div>

                          <div class="form_spaces1">
                            <div class="form-group">
                                <label>Contraseña</label>
                                <input type="text" name="password" id="password" class="form-control" name="password">
                            </div>
                        </div>
                        
                        <input type="submit" class="btn btn-info" value="Registrarse" name="Guardar">
                    </form>
            </div>
        </article>
    </main>

    <footer class="footer">
        <h6>© 2023 Cuponera Derechos reservados</h6>
    </footer>
</body>
</html>