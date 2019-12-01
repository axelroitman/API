package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.ImagenDAO;
import daos.ReclamoDAO;
import views.Estado;
import views.ImagenView;
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
		imagenes = getImagenes();
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
		imagenes = getImagenes();
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
		if(imagenes == null || imagenes.size() == 0)
		{
			imagenes = ImagenDAO.getInstancia().getImagenesOfReclamo(numero);
			
			if(imagenes.size() == 0) 
			{
				imagenes = new ArrayList<Imagen>();
			}
		}
		return imagenes;
	}
	
	public void cambiarEstado(Estado estado, String actualizacion) {
		this.estado = estado;
		if(this.actualizacion == null) 
		{
			this.actualizacion = actualizacion;
		}
		else 
		{
			this.actualizacion = this.actualizacion + "|@@|" + actualizacion;
		}
		ReclamoDAO.getInstancia().update(this);
	}

	public void save() {
		int idReclamo = ReclamoDAO.getInstancia().save(this);
		setNumero(idReclamo);
	}
	
	public void update() {
		ReclamoDAO.getInstancia().update(this);
	}
	
	public ReclamoView toView(){
		
		List<ImagenView> imagenesView = new ArrayList<ImagenView>();
		for(Imagen i : imagenes) 
		{
			imagenesView.add(i.toView());
		}
		
		if(unidad == null) 
		{
			return new ReclamoView(usuario.toView(), edificio.toView(), ubicacion, descripcion, null, estado, numero, imagenesView, actualizacion);				
		}
		else 
		{
			return new ReclamoView(usuario.toView(), edificio.toView(), ubicacion, descripcion, unidad.toView(), estado, numero, imagenesView, actualizacion);			
		}
	}
}
