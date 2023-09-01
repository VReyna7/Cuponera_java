package cuponera.www.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cuponera.www.beans.Cliente;


public class ClientesModel extends Conexion{
	public int insertarCliente(Cliente cliente) throws SQLException{
		 try {
			 int filasAfectadas = 0;
			 String sql = "INSERT INTO usuarios (dui, nombre, apellido, telefono, correo, direccion, contra, idEstado, idRol) VALUES (?,?,?,?,?,?,?,?,?)";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 cs.setString(1, cliente.getDui());
			 cs.setString(2, cliente.getNombre());
			 cs.setString(3, cliente.getApellido());
			 cs.setString(4, cliente.getTelefono());
			 cs.setString(5, cliente.getCorreo());
			 cs.setString(6, cliente.getDireccion());
			 cs.setString(7, cliente.getPassword());
			 cs.setInt(8, cliente.getEstado());
			 cs.setInt(9, 2);
			 filasAfectadas = cs.executeUpdate();
			 this.desconectar();
			 return filasAfectadas;
		 } catch (SQLException ex) {
			 Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return 0;
		 }
	}
	
	public List<Cliente> listarClientes() throws SQLException{
		try {
			List<Cliente> lista = new ArrayList<>();
			 String sql = "Select * from usuarios where idRol = 2";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 rs = cs.executeQuery();
			 while(rs.next()){
				 Cliente cliente= new Cliente();
				 cliente.setDui(rs.getString("dui"));
				 cliente.setNombre(rs.getString("nombre"));
				 cliente.setApellido(rs.getString("apellido"));
				 cliente.setTelefono(rs.getString("telefono"));
				 cliente.setCorreo(rs.getString("correo"));
				 cliente.setDireccion(rs.getString("direccion"));
				 lista.add(cliente);
			 }
			 this.desconectar();
			 return lista;
		}catch(SQLException ex) {
			Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			return null;
		}
	}
	
	public int eliminarCliente(String dui) throws SQLException{
		 try {
			 int filasAfectadas=0;
			 String sql = "Delete from usuario where dui = ?";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 cs.setString(1, dui);
			 filasAfectadas = cs.executeUpdate();
			 this.desconectar();
			 return filasAfectadas;
		 } catch (SQLException ex) {
			 Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return 0;
		 }
	}
	
	public int modificar(Cliente cliente) throws SQLException{
		 try {
			 int filasAfectadas=0;
			 String sql = "Update usuarios set nombre=?,apellido=?,telefono=?,correo=?,direccion=? where dui=?";
			 this.conectar();
			 cs = conexion.prepareCall(sql);
			 cs.setString(1, cliente.getNombre());
			 cs.setString(2, cliente.getApellido());
			 cs.setString(3, cliente.getTelefono());
			 cs.setString(4, cliente.getCorreo());
			 cs.setString(5, cliente.getDireccion());
			 cs.setString(6, cliente.getDui());
			 filasAfectadas = cs.executeUpdate();
			 this.desconectar();
			 return filasAfectadas;
		 } catch (SQLException ex) {
			 Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return 0;
		 }
	}
	
	
}
