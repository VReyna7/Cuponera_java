package cuponera.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.www.beans.Empresa;

public class EmpresasModel extends Conexion{

	public int insertarEmpresa(Empresa empresa) throws SQLException{
		 try {
			 int filasAfectadas = 0;
			 String sql = "INSERT INTO empresa (CodigoEmpresa, nombre, direccion, telkefono, correo, idRubro, comision, logo, password) VALUES (?,?,?,?,?,?,?,?,?)";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 cs.setString(1, empresa.getCodigo());
			 cs.setString(2, empresa.getNombre());
			 cs.setString(3, empresa.getDireccion());
			 cs.setString(4, empresa.getTelefono());
			 cs.setString(5, empresa.getCorreo());
			 cs.setInt(6, empresa.getIdRubro());
			 cs.setFloat(7, empresa.getComision());
			 cs.setString(8, empresa.getLogo());
			 cs.setString(9, empresa.getPassword());
			 filasAfectadas = cs.executeUpdate();
			 this.desconectar();
			 return filasAfectadas;
		 } catch (SQLException ex) {
			 Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return 0;
		 }
	}
	
	public List<Empresa> listarEmpresas() throws SQLException{
		try {
			List<Empresa> lista = new ArrayList<>();
			 String sql = "Select * from empresa";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 rs = cs.executeQuery();
			 while(rs.next()){
				 Empresa empresa= new Empresa();
				 empresa.setCodigo(rs.getString("CodigoEmpresa"));
				 empresa.setNombre(rs.getString("nombre"));
				 empresa.setDireccion(rs.getString("direccion"));
				 empresa.setTelefono(rs.getString("telefono"));
				 empresa.setCorreo(rs.getString("correo"));
				 empresa.setIdRubro(rs.getInt("idRubro"));
				 empresa.setComision(rs.getFloat("comision"));
				 empresa.setLogo(rs.getString("logo"));
				 lista.add(empresa);
			 }
			 this.desconectar();
			 return lista;
		}catch(SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			return null;
		}
	}
	
	public int modificarEmpresa(Empresa empresa) throws SQLException{
		 try {
			 int filasAfectadas = 0;
			 String sql = "Update empresa set nombre=?,direccion=?,telefono=?,correo=?,comision=?,logo=? where CodigoEmpresa = ?";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 cs.setString(1, empresa.getNombre());
			 cs.setString(2, empresa.getDireccion());
			 cs.setString(3, empresa.getTelefono());
			 cs.setString(4, empresa.getCorreo());
			 cs.setFloat(5, empresa.getComision());
			 cs.setString(6, empresa.getLogo());
			 cs.setString(7, empresa.getCodigo());
			 filasAfectadas = cs.executeUpdate();
			 this.desconectar();
			 return filasAfectadas;
		 } catch (SQLException ex) {
			 Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return 0;
		 }
	}
	
	public List<Empresa> filtradoEmpresas(int idRubro) throws SQLException{
		try {
			List<Empresa> lista = new ArrayList<>();
			 String sql = "Select * from empresa where idRubro=?";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 cs.setInt(1, idRubro);
			 rs = cs.executeQuery();
			 while(rs.next()){
				 Empresa empresa= new Empresa();
				 empresa.setCodigo(rs.getString("CodigoEmpresa"));
				 empresa.setNombre(rs.getString("nombre"));
				 empresa.setDireccion(rs.getString("direccion"));
				 empresa.setTelefono(rs.getString("telefono"));
				 empresa.setCorreo(rs.getString("correo"));
				 empresa.setIdRubro(rs.getInt("idRubro"));
				 empresa.setComision(rs.getFloat("comision"));
				 empresa.setLogo(rs.getString("logo"));
				 lista.add(empresa);
			 }
			 this.desconectar();
			 return lista;
		}catch(SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			return null;
		}
	}
	
	public int eliminarEmpresa(String codigo) throws SQLException{
		 try {
			 int filasAfectadas=0;
			 String sql = "Delete from empresa where CodigoEmpresa = ?";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 cs.setString(1, codigo);
			 filasAfectadas = cs.executeUpdate();
			 this.desconectar();
			 return filasAfectadas;
		 } catch (SQLException ex) {
			 Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return 0;
		 }
	}
}
