<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope and sessionScope.nombreUsuario == null}">
    <c:redirect url="logIn/inicioSesion.jsp"/>
</c:if>
<c:if test="${not empty sessionScope and sessionScope.nombreUsuario != null}">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="/Cabezera.jsp"/>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cuponesmostrar.css">
</head>
<body>
	   <nav class="navbar navbar-dark bg-dark justify-content-between">
            <span class="navbar-brand mb-0 h1 cuponera_title">LA CUPONERA</span>
            <div>
                <button class="inicio_sesion_button">Comprar Cupones</button>
                <button class="registrarse_button">Mis cupones</button>
            </div>
        </nav>

		<main>
            <div class="titulo">
                <h2 class="neonText">LISTA DE CUPONES</h2>
            </div>
            <div class="cupones_container">
               
			<c:forEach items="${requestScope.listaCupones}" var="cupon">
				 <div class="cupon_container">
		                    <div class="cupon">
		                        <div class="img_cupon" style="background-image: url('${pageContext.request.contextPath}/assets/img/${cupon.imagen}')">
		                        </div>
		                         <div class="info_cupon">
		                             <h4 class="titul_oferta">${cupon.titulo}</h4>
		                             <p><b>Descripcion: </b>${cupon.descripcion}</p>     
		                             <p><b>Fecha de inicio: </b>${cupon.fechaInicio}</p>
		                             <p><b>Disponble hasta el: </b>${cupon.fechaFinal}</p>
		                             <p><b>Uso posible hasta el: </b>${cupon.fechaLimite}</p>
		                             <p><b>Existencia:</b>
		                             <c:if test="${cupon.cantidadLimite != 0 }">
		                              ${cupon.cantidadLimite}
		                             </c:if>
		                              <c:if test="${cupon.cantidadLimite == 0 }">
		                               Sin limite
		                             </c:if>
		                             </p>
		                             <div class="precios_cupon">
		                                 <p><b>Antes: </b>${cupon.precioRegular}$</p>
		                                 <P><b>Ahora: </b>${cupon.precioOferta}$</P>
		                             </div>
		                         </div>
		                     </div>
		                     <button type="submit" class="btn btn-primary comprar_button">Comprar</button>
		                </div>
			</c:forEach>
               </div>
        </main>
	
	<footer class="footer">
            <h6>© 2023 Cuponera Derechos reservados</h6>
        </footer>

</body>
</html>
</c:if>
