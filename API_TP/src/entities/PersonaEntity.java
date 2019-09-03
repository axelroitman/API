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
	public PersonaEntity(String dni, String nombre) {
		super();
		this.documento = dni;
		this.nombre = nombre;
	}
	
}
