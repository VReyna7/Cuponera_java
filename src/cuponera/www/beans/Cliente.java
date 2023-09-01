package cuponera.www.beans;

public class Cliente {
	private String nombre;
	private String apellido;
	private String telefono;
	private String correo;
	private String direccion;
	private String dui;
	private String password;
	private int estado;
	
	//Constructor vacio
	public Cliente() {
		this.nombre = "";
		this.apellido = "";
		this.telefono = "";
		this.correo = "";
		this.direccion = "";
		this.dui = "";
		this.password = "";
		this.estado = 0;
	}
	
	//Constructor con parametros
	public Cliente(String nombre, String apellido, String telefono, String correo, String direccion, String dui,
			String password, int estado) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.dui = dui;
		this.password = password;
		this.estado = estado;
	}
	
	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
