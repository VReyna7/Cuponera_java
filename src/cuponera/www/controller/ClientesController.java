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

import cuponera.www.beans.Cliente;
import cuponera.www.controller.ClientesController;
import cuponera.www.utils.Validaciones;
import cuponera.www.utils.Utilities;
import cuponera.www.model.ClientesModel;

@WebServlet(name = "ClientesController", urlPatterns = {"/clientes.do"})
public class ClientesController extends HttpServlet {
	
	ArrayList<String> listaErrores = new ArrayList<>();
	ClientesModel modelo = new ClientesModel();
	Utilities passwordEncripted = new Utilities();
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 response.setContentType("text/html;charset=UTF-8");
			 try (PrintWriter out = response.getWriter()) {
				 String operacion = request.getParameter("op");
				 switch (operacion) {
					 case "listar":
						 listar(request, response);
					 break;
					 case "nuevo":
						 request.getRequestDispatcher("/clientes/registroClientes.jsp").forward(request, response);
					 break;
					 case "insertar":
						 insertar(request, response);
					 break;
					 case "obtener":
					 //obtener(request, response);
					 break;
					 case "editar":
						 request.getRequestDispatcher("").forward(request,response);
					 break;
					 case "modificar":
						 modificar(request,response);
					 break;
					 case "eliminar":
					 //eliminar(request, response);
					 break;
				 }
			 }
	}
	
	
    @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		processRequest(request, response);
	 }
	
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		processRequest(request, response);
	 }
	
	
   private void insertar(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 listaErrores.clear();
			 Cliente miCliente = new Cliente();
			 
			 int estado = 2;
			 String password = passwordEncripted.encriptarPassword(request.getParameter("password"));
			 
			 miCliente.setDui(request.getParameter("dui"));
			 miCliente.setNombre(request.getParameter("nombre"));
			 miCliente.setApellido(request.getParameter("apellido"));
			 miCliente.setTelefono(request.getParameter("telefono"));
			 miCliente.setCorreo(request.getParameter("email"));
			 miCliente.setDireccion(request.getParameter("direccion"));
			 miCliente.setPassword(password);
			 miCliente.setEstado(estado);
			 
			 if(Validaciones.campoEstaVacio(miCliente.getDui())){
				 listaErrores.add("ERROR. El campo DUI es obligatorio.");
			 }else  if (!Validaciones.validarDUI(miCliente.getDui())) {
				 listaErrores.add("ERROR. El formato del número de DUI es incorrecto (XXXXXXXX-X)");
			 }
			 
			 if (Validaciones.campoEstaVacio(miCliente.getNombre())) {
				 listaErrores.add("ERROR. El nombre del cliente es obligatorio.");
			 }
			 
			 if (Validaciones.campoEstaVacio(miCliente.getTelefono())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarTelefono(miCliente.getTelefono())) {
				 listaErrores.add("ERROR. El formato del número de telefono es incorrecto (XXXX-XXXX)");
			 }
			 
			 if (Validaciones.campoEstaVacio(miCliente.getCorreo())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarEmail(miCliente.getCorreo())) {
				 listaErrores.add("ERROR. El formato del correo electrónico es incorrecto.");
			 }
			 
			 
			 if (Validaciones.campoEstaVacio(miCliente.getDireccion())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }
			 
			 if (Validaciones.campoEstaVacio(miCliente.getPassword())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarPassword(miCliente.getPassword())) {
				 listaErrores.add("ERROR. La contraseña ingresada no cumple con los requisitos.");
			 }
			 
			 if (listaErrores.size() > 0) {
				 request.setAttribute("listaErrores", listaErrores);
				 request.setAttribute("cliente", miCliente);
				 request.getRequestDispatcher("clientes.do?op=nuevo").forward(request, response);
			 } else {
			 	 int registro = modelo.insertarCliente(miCliente);
				 if (registro > 0) {
					 request.getSession().setAttribute("exito", "Registro exitoso");
					 System.out.print("Registro Exitoso");
					 response.sendRedirect(request.getContextPath() + "/session.do?op=logIn");
				 } else {
					 request.getSession().setAttribute("fracaso", "No se ha podido realizar el registro");
					 System.out.print("Registro fallido: "+ registro);
					 response.sendRedirect(request.getContextPath() + "/clientes.do?op=nuevo");
				 }
			 }
		 } catch (IOException | SQLException | ServletException ex) {
			 Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
   
   private void modificar(HttpServletRequest request, HttpServletResponse response) {
	   try {
		   listaErrores.clear();
			 Cliente miCliente = new Cliente();
			 
			 miCliente.setDui(request.getParameter("dui"));
			 miCliente.setNombre(request.getParameter("nombre"));
			 miCliente.setApellido(request.getParameter("apellido"));
			 miCliente.setTelefono(request.getParameter("telefono"));
			 miCliente.setCorreo(request.getParameter("email"));
			 miCliente.setDireccion(request.getParameter("direccion"));
			 
			 if (Validaciones.campoEstaVacio(miCliente.getNombre())) {
				 listaErrores.add("ERROR. El nombre del cliente es obligatorio.");
			 }
			 if (Validaciones.campoEstaVacio(miCliente.getTelefono())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarTelefono(miCliente.getTelefono())) {
				 listaErrores.add("ERROR. El formato del número de telefono es incorrecto (XXXX-XXXX)");
			 }
			 if (Validaciones.campoEstaVacio(miCliente.getCorreo())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarEmail(miCliente.getCorreo())) {
				 listaErrores.add("ERROR. El formato del correo electrónico es incorrecto.");
			 }
			 if (Validaciones.campoEstaVacio(miCliente.getDireccion())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }
			 if (listaErrores.size() > 0) {
				 request.setAttribute("listaErrores", listaErrores);
				 request.setAttribute("cliente", miCliente);
				 request.getRequestDispatcher("clientes.do?op=editar").forward(request, response);
			 } else {
			 	 int actualizacion = modelo.modificar(miCliente);
				 if (actualizacion > 0) {
					 request.getSession().setAttribute("exito", "Actualizacion exitosa");
					 System.out.print("Registro Exitoso");
					 request.getRequestDispatcher("/clientes/indexCliente.jsp").forward(request, response);
				 } else {
					 request.getSession().setAttribute("fracaso", "No se ha podido realizar la actualizacion");
					 System.out.print("Actualizacion de datos fallida: "+ actualizacion);
					 response.sendRedirect(request.getContextPath() + "/clientes.do?op=editar");
				 }
			 }
	   }catch(IOException | SQLException | ServletException ex) {
		   Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
	   }
   }
   
   private void listar(HttpServletRequest request, HttpServletResponse response) {
	   try {
			request.setAttribute("listaClientes", modelo.listarClientes());
			request.getRequestDispatcher("").forward(request, response);
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
		 }
   }
   
   private void eliminar(HttpServletRequest request, HttpServletResponse response) {
	   try {
		   listaErrores.clear();
			 Cliente miCliente = new Cliente();
			 
			 String dui = request.getParameter("dui");
			 
			 if(Validaciones.campoEstaVacio(miCliente.getDui())){
				 listaErrores.add("ERROR. El campo DUI es obligatorio.");
			 }else  if (!Validaciones.validarDUI(miCliente.getDui())) {
				 listaErrores.add("ERROR. El formato del número de DUI es incorrecto (XXXXXXXX-X)");
			 }
			 if (listaErrores.size() > 0) {
				 request.setAttribute("listaErrores", listaErrores);
				 request.setAttribute("cliente", miCliente);
				 request.getRequestDispatcher("clientes.do?op=editar").forward(request, response);
			 } else {
			 	 int eliminar = modelo.eliminarCliente(dui);
				 if (eliminar > 0) {
					 request.getSession().setAttribute("exito", "Se elimino el cliente");
					 System.out.print("Registro Exitoso");
					 request.getRequestDispatcher("").forward(request, response);
				 } else {
					 request.getSession().setAttribute("fracaso", "No se ha podido eliminar el cliente");
					 System.out.print("Actualizacion de datos fallida: "+ eliminar);
					 response.sendRedirect(request.getContextPath() + "/clientes.do?op=editar");
				 }
			 }
	   }catch(IOException | SQLException | ServletException ex) {
		   Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
	   }
   }
}
