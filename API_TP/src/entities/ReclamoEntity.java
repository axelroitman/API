package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import views.Estado;

@Entity
@Table (name="reclamos")
public class ReclamoEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name= "idReclamo")
	int id;
	@Transient
	PersonaEntity usuario;
	@Transient
	EdificioEntity edificio;
	String ubicacion;
	String descripcion;
	@Transient
	UnidadEntity unidad;
	@Transient
	Estado estado;
	
	
	//List<Imagen> imagenes;
	
	public ReclamoEntity() {}


	public ReclamoEntity(PersonaEntity usuario, EdificioEntity edificio, String ubicación, String descripcion,
			UnidadEntity unidad, Estado estado) {
		super();
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicación;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public PersonaEntity getUsuario() {
		return usuario;
	}


	public void setUsuario(PersonaEntity usuario) {
		this.usuario = usuario;
	}


	public EdificioEntity getEdificio() {
		return edificio;
	}


	public void setEdificio(EdificioEntity edificio) {
		this.edificio = edificio;
	}


	public String getUbicación() {
		return ubicacion;
	}


	public void setUbicación(String ubicación) {
		this.ubicacion = ubicación;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public UnidadEntity getUnidad() {
		return unidad;
	}


	public void setUnidad(UnidadEntity unidad) {
		this.unidad = unidad;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}	
	
}
