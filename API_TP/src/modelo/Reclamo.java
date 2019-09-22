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
	private String ubicación;
	private String descripcion;
	private Unidad unidad;
	private Estado estado;
	private List<Imagen> imagenes;
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicación, String descripcion, Unidad unidad) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicación = ubicación;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = Estado.nuevo;
		imagenes = new ArrayList<Imagen>();
	}
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad, int numero, Estado estado) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicación = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
		this.numero = numero;
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

	public String getUbicación() {
		return ubicación;
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
		return new ReclamoView(usuario.toView(), edificio.toView(), ubicación, descripcion, unidad.toView(), estado);
	}
}
