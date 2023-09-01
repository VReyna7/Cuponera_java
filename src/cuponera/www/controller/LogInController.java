package cuponera.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cuponera.www.beans.Cliente;
import cuponera.www.controller.ClientesController;
import cuponera.www.utils.Utilities;
import cuponera.www.model.ClientesModel;
import cuponera.www.model.SessionModel;
import cuponera.www.model.CuponesModel;
import cuponera.www.beans.Cupon;

@WebServlet(name = "LogInController", urlPatterns = {"/session.do"})
public class LogInController extends HttpServlet {	
	
	ArrayList<String> listaErrores = new ArrayList<>();
	CuponesModel cupones = new CuponesModel();
	
    @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	 }

	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	 }
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 response.setContentType("text/html;charset=UTF-8");
			 try (PrintWriter out = response.getWriter()) {
				 String operacion = request.getParameter("op");
				 switch (operacion) {
					 case "logIn":
						 request.getRequestDispatcher("/logIn/inicioSesion.jsp").forward(request, response);
					 break;
					 case "ingresar":
					 	ingresar(request, response);
					 break;
					 case "logOut":
						 cerrarSesion(request, response);
						 
					 break;
				 }
			 }
	}
	
	private void ingresar(HttpServletRequest request, HttpServletResponse response) {
		listaErrores.clear(); 
		try {
			 Utilities decript = new Utilities();
			 SessionModel conexion = new SessionModel();
			 
			 Cliente cliente  = conexion.logIn(request.getParameter("email"));
			 
			 if(cliente != null) {
				 String password = request.getParameter("password");
				 
				 String encryptedPassword = cliente.getPassword();
				 if(encryptedPassword.length() > 0) {
					 boolean access = decript.verificarPassword(password, encryptedPassword);
					 if(access == true) {
							// Obtener la sesión actual o crear una si no existe
						     HttpSession session = request.getSession(true);
							 session.setAttribute("nombreUsuario", cliente.getNombre() + " " + cliente.getApellido());
							 request.setAttribute("listaCupones", cupones.listarCupones());
							 request.getRequestDispatcher("/clientes/indexCliente.jsp").forward(request, response);
						 }else {
							 request.getRequestDispatcher("/logIn/inicioSesion.jsp").forward(request, response);
							 listaErrores.add("ERROR.Contraseña incorrecta");
						 }
				 }else{
					 request.getRequestDispatcher("/logIn/inicioSesion.jsp").forward(request, response);
					 listaErrores.add("ERROR.Usuario no existente");
				 }
			 }			
		 } catch (IOException | SQLException | ServletException ex) {
			 Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
	
	public void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 HttpSession session = request.getSession(); // Obtener la sesión si existe
		  // Invalidar la sesión
		 if (session != null ) {
			 	session.invalidate();
			 	// Establecer el tiempo de inactividad en cero para invalidar la sesión
			    session.setMaxInactiveInterval(0);
			 	request.getRequestDispatcher("/logIn/inicioSesion.jsp").forward(request, response);
				System.out.print("invalido");
			} else {
				System.out.print("morido");
			}
		 
	}

}
