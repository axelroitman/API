package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="personas")

public class PersonaEntity {

	@Id
	private String documento;
	private String nombre;
	private String usuario;
	private String pass;
	private boolean administrador;
	private boolean activo;
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public PersonaEntity(){}
	
	public String getDni() {
		return documento;
	}
	public void setDni(String documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public PersonaEntity(String dni, String nombre, String usuario, String pass, boolean activo, boolean administrador) {
		super();
		this.documento = dni;
		this.nombre = nombre;
		this.usuario = usuario;
		this.pass = pass;
		this.activo = activo;
		this.administrador = administrador;

	}
	
}
