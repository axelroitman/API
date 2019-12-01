package modelo;

import daos.PersonaDAO;
import exceptions.PersonaException;
import views.PersonaView;

public class Persona {

	private String documento;
	private String nombre;
	private String usuario;
	private String pass;
	private boolean administrador;
	private boolean activo;
	
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

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public Persona(String documento, String nombre, String usuario, String pass, boolean activo, boolean administrador) {
		this.documento = documento;
		this.nombre = nombre;
		this.usuario = usuario;
		this.pass = pass;
		this.activo = activo;
		this.administrador = administrador;
	}

	public Persona(String documento, String usuario, String pass, boolean administrador) {
		this.documento = documento;
		this.usuario = usuario;
		this.pass = pass;
		this.administrador = administrador;	
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public PersonaView toView() {
		return new PersonaView(documento, nombre, usuario, pass, activo, administrador);
	}

	public void save() {
		PersonaDAO.getInstancia().save(this);
	}

	public void delete() {
		try {
			PersonaDAO.getInstancia().delete(this);
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
