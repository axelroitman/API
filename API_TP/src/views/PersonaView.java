package views;

public class PersonaView {
	
	private String documento;
	private String nombre;
	private String usuario;
	private String pass;
	private boolean activo;
	private boolean administrador;
	
	public PersonaView() {}

	public PersonaView(String documento, String nombre, String usuario, String pass, boolean activo, boolean administrador) {
		this.documento = documento;
		this.nombre = nombre;
		this.usuario = usuario;
		this.pass = pass;
		this.activo = activo;
		this.administrador = administrador;

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
		return documento + " " + nombre;
	}
	
}
