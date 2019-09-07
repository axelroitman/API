package views;

import java.util.List;

import modelo.Edificio;
import modelo.Imagen;
import modelo.Persona;
import modelo.Unidad;

public class ReclamoView {

	private int numero;
	private PersonaView usuario;
	private EdificioView edificio;
	private String ubicaci�n;
	private String descripcion;
	private UnidadView unidad;
	private Estado estado;
	
	//private List<ImagenView> imagenes; POR AHORA COMENTADO
	
	
	public ReclamoView(PersonaView usuario, EdificioView edificio, String ubicaci�n, String descripcion,
			UnidadView unidad, Estado estado) {
		super();
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicaci�n = ubicaci�n;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public PersonaView getUsuario() {
		return usuario;
	}
	public void setUsuario(PersonaView usuario) {
		this.usuario = usuario;
	}
	public EdificioView getEdificio() {
		return edificio;
	}
	public void setEdificio(EdificioView edificio) {
		this.edificio = edificio;
	}
	public String getUbicaci�n() {
		return ubicaci�n;
	}
	public void setUbicaci�n(String ubicaci�n) {
		this.ubicaci�n = ubicaci�n;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public UnidadView getUnidad() {
		return unidad;
	}
	public void setUnidad(UnidadView unidad) {
		this.unidad = unidad;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/*public List<ImagenView> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<ImagenView> imagenes) {
		this.imagenes = imagenes;
	}*/
}
