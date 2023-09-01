package cuponera.www.model;

import cuponera.www.beans.Cupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CuponesModel  extends Conexion{
	
	public List<Cupon> listarCupones() throws SQLException{
		
		try {
			List<Cupon> lista = new ArrayList<>();
			
			String sql = "SELECT * FROM oferta";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Cupon cupon = new Cupon();
				cupon.setCodigoOferta(rs.getNString("CodigoOferta"));
				cupon.setTitulo(rs.getNString("titulo"));
				cupon.setPrecioRegular(rs.getFloat("precioRegular"));
				cupon.setPrecioOferta(rs.getFloat("precioOferta"));
				cupon.setFechaInicio(rs.getDate("fechaInicio"));
				cupon.setFechaFinal(rs.getDate("fechaFin"));
				cupon.setCantidadLimite(rs.getInt("cantidadLimite"));
				cupon.setDescripcion(rs.getNString("descripcion"));
				cupon.setIdEmpresa(rs.getNString("idEmpresa"));
				cupon.setFechaLimite(rs.getDate("fechaLimite"));
				cupon.setEstado(rs.getInt("estado"));
				cupon.setImagen(rs.getNString("img"));
				cupon.setCategoria(rs.getNString("categoria"));
				lista.add(cupon);
			}
			this.desconectar();
			return lista;
		}catch(SQLException ex) {
			Logger.getLogger(CuponesModel.class.getName()).log(Level.SEVERE, null, ex);
			 this.desconectar();
			 return null;
		}
			
	}


}
