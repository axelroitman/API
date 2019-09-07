package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="inquilinos")
public class InquilinoEntity {
	//PRECISO UNA UNIDAD MANY TO ONE. Aparte una unidad puede tener muchos duenios
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "id")
	
	private UnidadEntity unidad;
	
	
	@OneToOne
	@JoinColumn(name="documento")
	private PersonaEntity persona;
	
	public InquilinoEntity() {}
	
	public InquilinoEntity(/*int id, UnidadEntity unidad,*/ PersonaEntity persona) {
		super();
		this.id = id;
		//this.unidad = unidad;
		this.persona = persona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public UnidadEntity getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadEntity unidad) {
		this.unidad = unidad;
	}*/

	public PersonaEntity getPersona() {
		return persona;
	}

	public void setPersona(PersonaEntity persona) {
		this.persona = persona;
	}
	
	
}
