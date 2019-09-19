package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="dbo.imagenes")
public class ImagenEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int numero;
	private String path;
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="idReclamo")
	private ReclamoEntity reclamo;
	
	public ImagenEntity() {}
	
	public ImagenEntity(int numero, String path, ReclamoEntity reclamo) {
		super();
		this.numero = numero;
		this.path = path;
		this.reclamo = reclamo;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ReclamoEntity getReclamo() {
		return reclamo;
	}

	public void setReclamo(ReclamoEntity reclamo) {
		this.reclamo = reclamo;
	}
}
