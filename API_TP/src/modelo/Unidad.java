package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.DuenioDAO;
import daos.InquilinoDAO;
import daos.UnidadDAO;
import exceptions.PersonaException;
import exceptions.UnidadException;
import views.EdificioView;
import views.UnidadView;

public class Unidad {

	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	private Edificio edificio;
	private List<Persona> duenios;
	private List<Persona> inquilinos;
	
	public Unidad(int id, String piso, String numero, Edificio edificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = false;
		this.edificio = edificio;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}

	public Unidad(int id, String piso, String numero, Edificio edificio, boolean habitado) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		this.edificio = edificio;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}

	public void transferir(Persona nuevoDuenio) throws PersonaException {
		duenios = getDuenios();
		for (Persona p : duenios)
		{
			DuenioDAO.getInstancia().delete(this, p);
		}
		duenios = new ArrayList<Persona>(); 
		duenios.add(nuevoDuenio);
		
		DuenioDAO.getInstancia().save(this, nuevoDuenio);
	}
	
	public void agregarDuenio(Persona duenio) throws UnidadException {
		boolean aparece = false;
		getDuenios();
		if(duenios.size() == 0) 
		{
			throw new UnidadException("No puede agregarse un dueño a unidad que no tiene dueños. Si se quiere cambiar el dueño, se debe realizar una transferencia.");
			
		}
		else 
		{
			for(Persona p : duenios)
			{
				if(p.getDocumento().equals(duenio.getDocumento()))
				{
					aparece = true;
				}
			}
			
			if(aparece == false)
			{
				duenios.add(duenio);
				DuenioDAO.getInstancia().save(this, duenio);
			}
			else 
			{
				throw new UnidadException("El dueño ya se encontraba registrado en esta unidad.");
				
			}
		}
	}
	
	public void alquilar(Persona inquilino) throws UnidadException {
		if(!this.habitado) {
			this.habitado = true;
			inquilinos = new ArrayList<Persona>();
			inquilinos.add(inquilino);
			
			UnidadDAO.getInstancia().update(this);
			InquilinoDAO.getInstancia().save(this, inquilino);
		}
		else
			throw new UnidadException("La unidad esta ocupada");
	}

	public void agregarInquilino(Persona inquilino) throws UnidadException {
		boolean aparece = false;
		getInquilinos();
		if(inquilinos.size() == 0) 
		{
			throw new UnidadException("No puede agregarse un inquilino a unidad que no se encuentra alquilada. En caso de querer alquilar una unidad, debe utilizarse el metodo alquilar.");
			
		}
		else {
			for(Persona p : inquilinos)
			{
				if(p.getDocumento().equals(inquilino.getDocumento()))
				{
					aparece = true;
				}
			}
			
			if(aparece == false)
			{
				inquilinos.add(inquilino);
				InquilinoDAO.getInstancia().save(this, inquilino);
			}
			else 
			{
				throw new UnidadException("El inquilino ya se encuentra registrado en esta unidad.");
			}
		}
	}
	
	public boolean estaHabitado() {
		return habitado;
	}
	
	public void liberar() {
		List<Persona> inq = getInquilinos();
		for(Persona inquilino : inq) 
		{
			InquilinoDAO.getInstancia().delete(this, inquilino);
		}
		this.inquilinos = new ArrayList<Persona>();
		this.habitado = false;
		UnidadDAO.getInstancia().update(this);
	}
	
	public void habitar() throws UnidadException {
		if(this.habitado)
			throw new UnidadException("La unidad ya esta habitada");
		else
		{
			this.habitado = true;
			UnidadDAO.getInstancia().update(this);
		}
	}
	
	public int getId() {
		return id;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	
	public Edificio getEdificio() {
		return edificio;
	}

	public List<Persona> getDuenios() {
		if(duenios == null || duenios.size() == 0)
		{
			try {
				duenios = DuenioDAO.getInstancia().findByIdentificador(id);
			} catch (PersonaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return duenios;
	}

	public List<Persona> getInquilinos() {
		if(inquilinos == null || inquilinos.size() == 0)
		{
			try {
				inquilinos = InquilinoDAO.getInstancia().findByIdentificador(id);
			} catch (PersonaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return inquilinos;
	}

	public UnidadView toView() {
		EdificioView auxEdificio = edificio.toView();
		return new UnidadView(id, piso, numero, habitado, auxEdificio);
	}
}
