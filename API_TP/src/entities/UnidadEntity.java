package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import daos.EdificioDAO;
import modelo.Edificio;
import modelo.Persona;

@Entity
@Table(name="dbo.unidades")
public class UnidadEntity {

	@Id
	@Column(name="identificador")
	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	
	@ManyToOne
	@JoinColumn(name="codigoEdificio")
	private EdificioEntity edificio;
	
	@Transient	
	private List<Persona> duenios;
	@Transient	
	private List<Persona> inquilinos;
	
	//@Column(name="edificioCodigo")
	
	public UnidadEntity() {
		
	}

	public UnidadEntity(int id, String piso, String numero, EdificioEntity edificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = false;
		this.edificio = edificio;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isHabitado() {
		return habitado;
	}

	public void setHabitado(boolean habitado) {
		this.habitado = habitado;
	}

	public EdificioEntity getEdificio() {
		return edificio;
	}

	public void setEdificio(EdificioEntity edificio) {
		this.edificio = edificio;
	}

	public List<Persona> getDuenios() {
		return duenios;
	}

	public void setDuenios(List<Persona> duenios) {
		this.duenios = duenios;
	}

	public List<Persona> getInquilinos() {
		return inquilinos;
	}

	public void setInquilinos(List<Persona> inquilinos) {
		this.inquilinos = inquilinos;
	}
	
	


}
