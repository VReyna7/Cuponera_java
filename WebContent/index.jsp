<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio</title>
	<jsp:include page="/Cabezera.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
</head>
<body>

                        <!-- As a heading -->
        <nav class="navbar navbar-dark bg-dark justify-content-between">
            <span class="navbar-brand mb-0 h1 cuponera_title">LA CUPONERA</span>
            <div>
                <a href="${pageContext.request.contextPath}/session.do?op=logIn"><button class="inicio_sesion_button">Iniciar sesión</button></a>
               <a href="${pageContext.request.contextPath}/clientes.do?op=nuevo"> <button class="registrarse_button">Registrarse</button></a>
            </div>
        </nav>
        <!--Fin navbar -->

        <main>
            <article class="main_article">
                <div class="div_main">
                    <div class="frase_container">
                        <h2 class="frase_1">APOYANDO</h2>
                        <h2 class="frase_2">TÚ</h2>
                        <h2 class="frase_3">CARTERA</h2>
                        <img src="assets/img/Cuponera.jpg" alt="imagen-gift" class="image_index">
                    </div>
                    <div class="cupones_totales">
                        <h2 class="cuponesDisponibles_title">Cupones disponibles</h2>
                        <h2 class="cantidad_cupones">90</h2>
                        <h2 class="tiempo_real_text">Contador</h2>
                        <button class="ver_cupones">Ver cupones</button>
                    </div>
                </div>
            </article>
        </main>
       
        <footer class="footer">
            <h6>© 2023 Cuponera Derechos reservados</h6>
        </footer>
</body>
</html>