package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.ReclamoDAO;
import views.Estado;
import views.ReclamoView;

public class Reclamo {

	private int numero;
	private Persona usuario;
	private Edificio edificio;
	private String ubicacion;
	private String descripcion;
	private Unidad unidad;
	private Estado estado;
	private List<Imagen> imagenes;
	private String actualizacion;
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad, String actualizacion) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = Estado.nuevo;
		this.actualizacion = actualizacion;
		imagenes = new ArrayList<Imagen>();
	}
	
	public String getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad, int numero, Estado estado, String actualizacion) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
		this.numero = numero;
		this.actualizacion = actualizacion;
		imagenes = new ArrayList<Imagen>();
	}


	public void agregarImagen(String direccion, String tipo) {
		Imagen imagen = new Imagen(direccion, tipo);
		imagenes.add(imagen);
		imagen.save(numero);
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Persona getUsuario() {
		return usuario;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public List<Imagen> getImagenes(){
		return this.imagenes;
	}
	
	public void cambiarEstado(Estado estado) {
		this.estado = estado;
		ReclamoDAO.getInstancia().update(this);
	}

	public void save() {
		ReclamoDAO.getInstancia().save(this);
	}
	
	public void update() {
		ReclamoDAO.getInstancia().update(this);
	}
	
	public ReclamoView toView(){
		return new ReclamoView(usuario.toView(), edificio.toView(), ubicacion, descripcion, unidad.toView(), estado, numero);
	}
}
