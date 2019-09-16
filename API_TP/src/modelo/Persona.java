package modelo;

import daos.PersonaDAO;
import views.PersonaView;

public class Persona {

	private String documento;
	private String nombre;
		
	public Persona(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public PersonaView toView() {
		return new PersonaView(documento, nombre);
	}

	public void save() {
		PersonaDAO.getInstancia().save(this);
	}

	public void delete() {
		//PersonaDAO.getInstancia().delete(this); HACER EN EL DAO (AL BORRAR CHEQUEAR SI TIENE UNIDADES ASOCIADAS O NO, ETC)
	}	
}
