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

import cuponera.www.beans.Empresa;
import cuponera.www.controller.EmpresaController;
import cuponera.www.utils.Validaciones;
import cuponera.www.utils.Utilities;
import cuponera.www.model.EmpresasModel;

@WebServlet(name = "EmpresaController", urlPatterns = {"/empresas.do"})
public class EmpresaController extends HttpServlet {
	
	ArrayList<String> listaErrores = new ArrayList<>();
	EmpresasModel modelo = new EmpresasModel();
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
						 request.getRequestDispatcher("/empresas/").forward(request, response);
					 break;
					 case "insertar":
						 insertar(request, response);
					 break;
					 case "obtener":
						 obtener(request, response);
					 break;
					 case "editar":
						 request.getRequestDispatcher("").forward(request,response);
					 break;
					 case "modificar":
						 modificarEmpresa(request,response);
					 break;
					 case "eliminar":
						 eliminarEmpresa(request, response);
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
			 Empresa empresa = new Empresa();
			 
			 String password = passwordEncripted.encriptarPassword(request.getParameter("password"));
			 
			 empresa.setCodigo(request.getParameter("codigo"));
			 empresa.setNombre(request.getParameter("nombre"));
			 empresa.setDireccion(request.getParameter("direccion"));
			 empresa.setTelefono(request.getParameter("telefono"));
			 empresa.setCorreo(request.getParameter("email"));
			 empresa.setIdRubro(Integer.parseInt(request.getParameter("rubro")));
			 empresa.setComision(Float.parseFloat(request.getParameter("comision")));
			 empresa.setLogo(request.getParameter("logo"));
			 empresa.setPassword(password);
			 
			 if(Validaciones.campoEstaVacio(empresa.getCodigo())){
				 listaErrores.add("ERROR. El campo DUI es obligatorio.");
			 }else  if (!Validaciones.validarCodigoEmpresa(empresa.getCodigo())) {
				 listaErrores.add("ERROR. El formato del número de DUI es incorrecto (XXXXXXXX-X)");
			 }
			 
			 if (Validaciones.campoEstaVacio(empresa.getNombre())) {
				 listaErrores.add("ERROR. El nombre del cliente es obligatorio.");
			 }
			 
			 if (Validaciones.campoEstaVacio(empresa.getTelefono())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarTelefono(empresa.getTelefono())) {
				 listaErrores.add("ERROR. El formato del número de telefono es incorrecto (XXXX-XXXX)");
			 }
			 
			 if (Validaciones.campoEstaVacio(empresa.getCorreo())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarEmail(empresa.getCorreo())) {
				 listaErrores.add("ERROR. El formato del correo electrónico es incorrecto.");
			 }
			 
			 
			 if (Validaciones.campoEstaVacio(empresa.getDireccion())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }
			 
			 if (Validaciones.campoEstaVacio(empresa.getPassword())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarPassword(empresa.getPassword())) {
				 listaErrores.add("ERROR. La contraseña ingresada no cumple con los requisitos.");
			 }
			 
			 if (listaErrores.size() > 0) {
				 request.setAttribute("listaErrores", listaErrores);
				 request.setAttribute("empresa", empresa);
				 request.getRequestDispatcher("empresas.do?op=nuevo").forward(request, response);
			 } else {
			 	 int registro = modelo.insertarEmpresa(empresa);
				 if (registro > 0) {
					 request.getSession().setAttribute("exito", "Registro exitoso");
					 System.out.print("Registro Exitoso");
					 response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
				 } else {
					 request.getSession().setAttribute("fracaso", "No se ha podido realizar el registro");
					 System.out.print("Registro fallido: "+ registro);
					 response.sendRedirect(request.getContextPath() + "/empresas.do?op=nuevo");
				 }
			 }
		 } catch (IOException | SQLException | ServletException ex) {
			 Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
		 }
	}
   
   private void modificarEmpresa(HttpServletRequest request, HttpServletResponse response) {
	   try {
		   listaErrores.clear();
			 Empresa empresa = new Empresa();
			 
			 empresa.setCodigo(request.getParameter("codigo"));
			 empresa.setNombre(request.getParameter("nombre"));
			 empresa.setDireccion(request.getParameter("direccion"));
			 empresa.setTelefono(request.getParameter("telefono"));
			 empresa.setCorreo(request.getParameter("email"));
			 empresa.setIdRubro(Integer.parseInt(request.getParameter("rubro")));
			 empresa.setComision(Float.parseFloat(request.getParameter("comision")));
			 empresa.setLogo(request.getParameter("logo"));
			 
			 if (Validaciones.campoEstaVacio(empresa.getNombre())) {
				 listaErrores.add("ERROR. El nombre del cliente es obligatorio.");
			 }
			 
			 if (Validaciones.campoEstaVacio(empresa.getTelefono())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarTelefono(empresa.getTelefono())) {
				 listaErrores.add("ERROR. El formato del número de telefono es incorrecto (XXXX-XXXX)");
			 }
			 
			 if (Validaciones.campoEstaVacio(empresa.getCorreo())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }else if (!Validaciones.validarEmail(empresa.getCorreo())) {
				 listaErrores.add("ERROR. El formato del correo electrónico es incorrecto.");
			 }
			 
			 
			 if (Validaciones.campoEstaVacio(empresa.getDireccion())) {
				 listaErrores.add("La nacionalidad es obligatoria");
			 }
			 
			 if (listaErrores.size() > 0) {
				 request.setAttribute("listaErrores", listaErrores);
				 request.setAttribute("empresa", empresa);
				 request.getRequestDispatcher("empresas.do?op=nuevo").forward(request, response);
			 } else {
			 	 int registro = modelo.modificarEmpresa(empresa);
				 if (registro > 0) {
					 request.getSession().setAttribute("exito", "Registro exitoso");
					 System.out.print("Registro Exitoso");
					 response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
				 } else {
					 request.getSession().setAttribute("fracaso", "No se ha podido realizar el registro");
					 System.out.print("Registro fallido: "+ registro);
					 response.sendRedirect(request.getContextPath() + "/empresas.do?op=nuevo");
				 }
			 }
	   }catch(IOException | SQLException | ServletException ex) {
		   Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
	   }
   }
   
   private void listar(HttpServletRequest request, HttpServletResponse response) {
	   try {
			request.setAttribute("listaEmpresas", modelo.listarEmpresas());
			request.getRequestDispatcher("").forward(request, response);
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
		 }
   }
   
   private void eliminarEmpresa(HttpServletRequest request, HttpServletResponse response) {
	   try {
		   listaErrores.clear();
			 Empresa empresa = new Empresa();
			 
			 String codigo = request.getParameter("codigo");
			 
			 if(Validaciones.campoEstaVacio(empresa.getCodigo())){
				 listaErrores.add("ERROR. El campo DUI es obligatorio.");
			 }else  if (!Validaciones.validarCodigoEmpresa(empresa.getCodigo())) {
				 listaErrores.add("ERROR. El formato del número de DUI es incorrecto (XXXXXXXX-X)");
			 }
			 
			 if (listaErrores.size() > 0) {
				 request.setAttribute("listaErrores", listaErrores);
				 request.setAttribute("empresa", empresa);
				 request.getRequestDispatcher("empresas.do?op=nuevo").forward(request, response);
			 } else {
			 	 int eliminar = modelo.eliminarEmpresa(codigo);
				 if (eliminar > 0) {
					 request.getSession().setAttribute("exito", "Registro exitoso");
					 System.out.print("Registro Exitoso");
					 response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
				 } else {
					 request.getSession().setAttribute("fracaso", "No se ha podido realizar el registro");
					 System.out.print("Registro fallido: "+ eliminar);
					 response.sendRedirect(request.getContextPath() + "/empresas.do?op=nuevo");
				 }
			 }
	   }catch(IOException | SQLException | ServletException ex) {
		   Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
	   }
   }
   
   private void obtener(HttpServletRequest request, HttpServletResponse response) {
	   try {
		   	int rubro= Integer.parseInt(request.getParameter("idRubro"));
			request.setAttribute("flitroEmpresas", modelo.filtradoEmpresas(rubro));
			request.getRequestDispatcher("").forward(request, response);
		 } catch (SQLException | ServletException | IOException ex) {
			 Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
		 }
   }
}
