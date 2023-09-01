package cuponera.www.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.www.beans.Cliente;

public class SessionModel extends Conexion{
	public Cliente logIn(String email) throws SQLException{
		Cliente cliente = new Cliente();
		 try {
			 String sql = "SELECT * FROM usuarios WHERE Correo = ?";
		        this.conectar();
		        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
		        preparedStatement.setString(1, email);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        int rowCount = 0;
		        while (resultSet.next()) {
		            rowCount++;
		            if(rowCount == 1) {
		            	cliente.setDui(resultSet.getString("dui"));
			            cliente.setNombre(resultSet.getString("nombre"));
			            cliente.setApellido(resultSet.getString("apellido"));
			            cliente.setTelefono(resultSet.getString("telefono"));
			            cliente.setCorreo(resultSet.getString("correo"));
			            cliente.setDireccion(resultSet.getString("direccion"));
			            cliente.setPassword(resultSet.getString("contra"));
			            cliente.setEstado(Integer.parseInt(resultSet.getString("idEstado")));
		            }
		        }
		        this.desconectar();
		        return cliente;
		 } catch (SQLException ex) {
			 Logger.getLogger(ClientesModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return null;
		 }
	}
}
