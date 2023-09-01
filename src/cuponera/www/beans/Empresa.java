package cuponera.www.beans;

public class Empresa {
	private String codigo;
	private String nombre;
	private String direccion;
	private String telefono;
	private String correo;
	private int idRubro;
	private float comision;
	private String logo;
	private String password;
	
	//Constructores
	public Empresa(String codigo, String nombre, String direccion, String telefono, String correo, int idRubro,
			float comision, String logo, String password) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.idRubro = idRubro;
		this.comision = comision;
		this.logo = logo;
		this.password = password;
	}
	
	public Empresa() {
		this.codigo = "";
		this.nombre = "";
		this.direccion = "";
		this.telefono = "";
		this.correo = "";
		this.idRubro = 0;
		this.comision = 0;
		this.logo = "";
		this.password = "";
	}

	//Getter y setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
