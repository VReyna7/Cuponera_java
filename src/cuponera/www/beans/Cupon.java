package cuponera.www.beans;

import java.util.Date;

public class Cupon {
	private String codigoOferta;
	private String titulo;
	private float precioOferta;
	private float precioRegular;
	private Date fechaInicio;
	private Date fechaFinal;
	private int cantidadLimite;
	private String descripcion;
	private String idEmpresa;
	private Date fechaLimite;
	private int cantidadVenta;
	private int estado;
	private String imagen;
	private String categoria;
	
	public String getCodigoOferta() {
		return codigoOferta;
	}
	public void setCodigoOferta(String codigoOferta) {
		this.codigoOferta = codigoOferta;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public float getPrecioOferta() {
		return precioOferta;
	}
	public void setPrecioOferta(float precioOferta) {
		this.precioOferta = precioOferta;
	}
	public float getPrecioRegular() {
		return precioRegular;
	}
	public void setPrecioRegular(float precioRegular) {
		this.precioRegular = precioRegular;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public int getCantidadLimite() {
		return cantidadLimite;
	}
	public void setCantidadLimite(int cantidadLimite) {
		this.cantidadLimite = cantidadLimite;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Date getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public int getCantidadVenta() {
		return cantidadVenta;
	}
	public void setCantidadVenta(int cantidadVenta) {
		this.cantidadVenta = cantidadVenta;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
}
