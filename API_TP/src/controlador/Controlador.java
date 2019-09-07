package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import daos.EdificioDAO;
import daos.InquilinoDAO;
import daos.PersonaDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import views.EdificioView;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class Controlador {

	private static Controlador instancia;
	
	private Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	private Persona aBuscar;
	
	public List<EdificioView> getEdificios(){
		List<EdificioView> resultado = new ArrayList<EdificioView>();
		List<Edificio> edificios = new EdificioDAO().getEdificios();
		for(Edificio edificio : edificios)
			resultado.add(edificio.toView());
		return resultado;
	}
	public List<PersonaView> getPersonas(){
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		List<Persona> personas = new PersonaDAO().getPersonas();
		for(Persona persona : personas)
			resultado.add(persona.toView());
		return resultado;
	}
	
	public List<Persona> getInquilinos(){
		List<Persona> resultado = new ArrayList<Persona>();
		resultado = new InquilinoDAO().getInquilinos();
		return resultado;
	}
	
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException{
		List<UnidadView> resultado = new ArrayList<UnidadView>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> unidades = edificio.getUnidades();
		for(Unidad unidad : unidades)
			resultado.add(unidad.toView());
		return resultado;
	}
	
	public List<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		for(Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> duenios = edificio.duenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.duenios();
		for(Persona persona : habitantes)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> inquilinos = unidad.getInquilinos();
		for(Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}
	
	public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.transferir(persona);
	}

	public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarDuenio(persona);
	}

	public void alquilarUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.alquilar(persona);
	}

	public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarInquilino(persona);
	}

	public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.liberar();
	}
	
	public void habitarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.habitar();;
	}
	
	public void agregarPersona(String documento, String nombre) {
		Persona persona = new Persona(documento, nombre);
		persona.save();
	}
	
	public void eliminarPersona(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		persona.delete();
	}
	
	public List<ReclamoView> reclamosPorEdificio(int codigo){
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorUnidad(int codigo, String piso, String numero) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		return resultado;
	}
	
	public ReclamoView reclamosPorNumero(int numero) {
		ReclamoView resultado = null;
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorPersona(String documento) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		return resultado;
	}
 
	public int agregarReclamo(int codigo, String piso, String numero, String documento, String ubicación, String descripcion) throws EdificioException, UnidadException, PersonaException {
		Edificio edificio = buscarEdificio(codigo);
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		Reclamo reclamo = new Reclamo(persona, edificio, ubicación, descripcion, unidad);
		reclamo.save();
		return reclamo.getNumero();
	}
	
	public void agregarImagenAReclamo(int numero, String direccion, String tipo) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.agregarImagen(direccion, tipo);
	}
	
	public void cambiarEstado(int numero, Estado estado) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.cambiarEstado(estado);
		reclamo.update();
	}
	
	private Edificio buscarEdificio(int codigo) throws EdificioException {
		Edificio edificio = new EdificioDAO().findById(codigo);
		return edificio;

	}
	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException{
		return null;
	}	
	
	private Persona buscarPersona(String documento) {
		try {
			aBuscar = new PersonaDAO().findById(documento);
		} catch (PersonaException e) {
			e.printStackTrace();
		}
		return aBuscar;			
	}
	
	private Reclamo buscarReclamo(int numero) throws ReclamoException {
		return null;
	}
}
