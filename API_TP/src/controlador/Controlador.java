package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import daos.EdificioDAO;
import daos.InquilinoDAO;
import daos.PersonaDAO;
import daos.ReclamoDAO;
import daos.UnidadDAO;
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
	
	public List<EdificioView> getEdificios(){ //hecho
		List<EdificioView> resultado = new ArrayList<EdificioView>();
		List<Edificio> edificios = EdificioDAO.getInstancia().getEdificios();
		for(Edificio edificio : edificios)
			resultado.add(edificio.toView());
		return resultado;
	}
	public List<PersonaView> getPersonas(){ //hecho 
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		List<Persona> personas = new PersonaDAO().getPersonas();
		for(Persona persona : personas)
			resultado.add(persona.toView());
		return resultado;
	}
	
	public List<Persona> getInquilinos(){ //hecho
		List<Persona> resultado = new ArrayList<Persona>();
		resultado = InquilinoDAO.getInstancia().getInquilinos();
		return resultado;
	}
	
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException, UnidadException{ //hecho
		List<UnidadView> resultado = new ArrayList<UnidadView>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> unidades = edificio.getUnidades();
		for(Unidad unidad : unidades)
			resultado.add(unidad.toView());
		return resultado;
	}
	
	public List<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException{ //hecho
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		for(Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException{ //hecho
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> duenios = edificio.duenios();
		for(Persona persona : duenios)
		{
			resultado.add(persona.toView());
		}
		return resultado;
	}

	public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.habitantes(); 
		
		/*El método habitantes() devuelve solo a los que habitan unidades,
		 y en la BD todas las unidades tienen una N en "habitado"*/
		
		for(Persona persona : habitantes)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException{ //HECHO
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{ //HECHO
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> inquilinos = unidad.getInquilinos();
		for(Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}
	
	public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException { //hecho
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.transferir(persona);
	}

	public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException { //hecho
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
		Persona buscoPersona = null;		
		try {
			buscoPersona = PersonaDAO.getInstancia().findById(documento);
		} catch (PersonaException e) {
			e.printStackTrace();
		}
		
		if(buscoPersona == null)
		{	
			persona.save();
		}
		else
		{
			System.out.println("Ya existe la persona.");
		}
	}
	
	public void eliminarPersona(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		persona.delete();
	}
	
	
	public List<ReclamoView> reclamosPorEdificio(int codigo){  //hecho. Falta prueba
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> reclamos = ReclamoDAO.getInstancia().getReclamos();
		
		if(!reclamos.isEmpty() || reclamos != null) {
			for(Reclamo r : reclamos) {
					if(r.getEdificio().getCodigo() == codigo) {
						resultado.add(r.toView());
					}
				}
		}
		
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorUnidad(int codigo, String piso, String numero) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		return resultado;
	}
	
	public ReclamoView reclamosPorNumero(int numero) { //Hecho
		Reclamo reclamo;
		ReclamoView resultado = null;
		try {
			reclamo = ReclamoDAO.getInstancia().findById(numero);
			resultado = reclamo.toView();

		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorPersona(String documento) { //Hecho
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		List<Reclamo> reclamos = ReclamoDAO.getInstancia().findByDocumento(documento);

		for(Reclamo r: reclamos)
		{
			resultado.add(r.toView());
		}
		return resultado;
	}
 
	public int agregarReclamo(int codigo, String piso, String numero, String documento, String ubicación, String descripcion) throws EdificioException, UnidadException, PersonaException {
		//Hecho, pero falta el tema del estado.
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
	
	private Edificio buscarEdificio(int codigo) throws EdificioException { //hecho
		Edificio aBuscar = null;
		try {
			aBuscar = EdificioDAO.getInstancia().findById(codigo);
		} catch (EdificioException e) {
			e.printStackTrace();
		}
		return aBuscar;	

	}
	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException{ //hecho
		Unidad aBuscar = null;
		try {
			aBuscar = UnidadDAO.getInstancia().find(codigo, piso, numero);
		} catch (UnidadException e) {
			e.printStackTrace();
		}
		return aBuscar;				
	}	
	
	private Persona buscarPersona(String documento) throws PersonaException { //hecho
		Persona aBuscar = null;
		try {
			aBuscar = PersonaDAO.getInstancia().findById(documento);
		} catch (PersonaException e) {
			throw new PersonaException("No Existe la Persona");
		}
		return aBuscar;				
	}
	
	private Reclamo buscarReclamo(int numero) throws ReclamoException { //hecho
		Reclamo aBuscar = null;
		try {
			aBuscar = ReclamoDAO.getInstancia().findById(numero);
		} catch (ReclamoException e) {
			e.printStackTrace();
		}
		return aBuscar;
	}
}
