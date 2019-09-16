package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import controlador.Controlador;
import entities.DuenioEntity;
import entities.InquilinoEntity;
import entities.PersonaEntity;
import entities.UnidadEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Persona;
import modelo.Unidad;
import views.PersonaView;

public class DuenioDAO {
	
	private static DuenioDAO instancia;
	
	public static DuenioDAO getInstancia() {
		if(instancia == null)
			instancia = new DuenioDAO();
		return instancia;
	}
	
	public List<Persona> getDuenios(){
		
		List<Persona> resultado = new ArrayList<Persona>();
		List<DuenioEntity> personasDuen = new ArrayList<DuenioEntity>();

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		personasDuen = (List<DuenioEntity>) s.createQuery("select d from DuenioEntity d inner join d.persona").list();
		s.getTransaction().commit();
		
		for(DuenioEntity de : personasDuen)
			resultado.add(PersonatoNegocio(de));
		s.close();
		
		return resultado;
	}
	
public Unidad getUnidadPorDuenioId(int id) throws PersonaException{
		
		DuenioEntity duenio = new DuenioEntity();
		UnidadEntity rdo = new UnidadEntity();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		duenio = (DuenioEntity) s.createQuery("select i from DuenioEntity i where i.id = ?").setInteger(0, id).uniqueResult();
		if(duenio == null) {
			throw new PersonaException("No existe el duenio " + id);
		}
		rdo = (UnidadEntity) s.createQuery("select u from UnidadEntity u where u.id = ?").setInteger(0, duenio.getUnidad().getId()).uniqueResult();
		s.getTransaction().commit();		
		s.close();
		
		return UnidadDAO.getInstancia().toNegocio(rdo);
	}
	
	public Persona findById(int id) throws PersonaException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		DuenioEntity duenio = (DuenioEntity) s.createQuery("from DuenioEntity d where d.id = ? ")
				.setInteger(0, id).uniqueResult();
		if(duenio == null)
			throw new PersonaException("No existe el duenio " + id);
		return PersonatoNegocio(duenio);
	}
	
	public List<Persona> findByIdentificador(int id) throws PersonaException {
		List<Persona> duenios = new ArrayList<Persona>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
			List<DuenioEntity> dueniosEntity = s.createQuery("from DuenioEntity d where d.unidad.id = ? ")
					.setInteger(0, id).list();
			if(dueniosEntity == null)
			{
				throw new PersonaException("No existen duenios.");
			}
			else 
			{
				for(DuenioEntity d: dueniosEntity) 
				{
					Persona per = PersonatoNegocio(d);
					duenios.add(per);
				}
			}
		return duenios;
	}


	public void save(Unidad unidad, Persona duenio){
		DuenioEntity aGrabar = toEntity(unidad, duenio);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Unidad unidad, Persona duenio){
		DuenioEntity aGrabar = toEntity(unidad, duenio);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void delete(Unidad unidad, Persona duenio){
		//DuenioEntity aEliminar = toEntity(unidad, duenio);
		//System.out.println(aEliminar.getPersona().getDni());

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		DuenioEntity aEliminar = (DuenioEntity) s.createQuery("from DuenioEntity i where i.persona.documento = ? and i.unidad.id = ?")
				.setString(0, duenio.getDocumento())
				.setInteger(1, unidad.getId())
				.uniqueResult();
		s.delete(aEliminar);
		s.getTransaction().commit();
		s.close();
	}
	
	
	private DuenioEntity toEntity(Unidad unidad, Persona persona){ 
		return new DuenioEntity(UnidadDAO.getInstancia().toEntity(unidad),new PersonaDAO().getInstancia().toEntity(persona));
	} 
	
	private Persona PersonatoNegocio(DuenioEntity de){
		return new Persona (de.getPersona().getDni(), de.getPersona().getNombre());
	}

	public List<Unidad> unidadesPorDuenio(Persona duenio){
		List<Unidad> resultado = new ArrayList<Unidad>();
		List<Unidad> un = UnidadDAO.getInstancia().getUnidades();
		for(Unidad unidad : un) {
			List<Persona> due = unidad.getDuenios();
			if(due.contains(duenio)) {
				resultado.add(unidad);
			}
		}
		return resultado;
	}
	
}