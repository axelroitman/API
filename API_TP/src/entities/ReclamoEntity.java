package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import modelo.Imagen;
import views.Estado;

@Entity
@Table (name="reclamos")
public class ReclamoEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name= "idReclamo")
	int id;
	
	@ManyToOne
	@JoinColumn(name= "documento")
	PersonaEntity usuario;
	
	@ManyToOne
	@JoinColumn(name= "codigo")	
	EdificioEntity edificio;
	
	String ubicacion;
	String descripcion;
	
	@ManyToOne
	@JoinColumn(name= "identificador")	
	UnidadEntity unidad;
	
	int estado;
	
	@Transient
	List<ImagenEntity> imagenes;
	
	public ReclamoEntity() {}


	public ReclamoEntity(PersonaEntity usuario, EdificioEntity edificio, String ubicacion, String descripcion,
			UnidadEntity unidad, Estado estado) {
		super();
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.imagenes= new ArrayList<ImagenEntity>();
		setEstado(estado);
	}


	public List<ImagenEntity> getImagenes() {
		return imagenes;
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


	public String getUbicaci�n() {
		return ubicacion;
	}


	public void setUbicaci�n(String ubicaci�n) {
		this.ubicacion = ubicaci�n;
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

	public void setImagenes(List<ImagenEntity> imagenes) {
		this.imagenes = imagenes;
	}


	public Estado getEstado() {
		Estado est = null;
		if(estado == 1) {
			est = Estado.nuevo;
		}
		else if (estado == 2) {
			est= Estado.abierto;
		}
		else if(estado == 3) {
			est= Estado.enProceso;
		}
		else if(estado == 4) {
			est= Estado.desestimado;
		}
		else if(estado == 5) {
			est= Estado.anulado;
		}
		else if(estado == 6) {
			est= Estado.terminado;
		}
		return est;
	}


	public void setEstado(Estado estado) {
		
		if(Estado.nuevo == estado) {
			this.estado= 1;
		}
		else if (Estado.abierto == estado) {
			this.estado= 2;
		}
		else if(Estado.enProceso == estado) {
			this.estado= 3;
		}
		else if(Estado.desestimado == estado) {
			this.estado= 4;
		}
		else if(Estado.anulado == estado) {
			this.estado= 5;
		}
		else if(Estado.terminado == estado) {
			this.estado= 6;
		}
	}	
	
}
