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

public class InquilinoDAO {
	
	private static InquilinoDAO instancia;
	
	public static InquilinoDAO getInstancia() {
		if(instancia == null)
			instancia = new InquilinoDAO();
		return instancia;
	}
	
	public List<Persona> getInquilinos(){
		
		List<Persona> resultado = new ArrayList<Persona>();
		List<Persona> personas = PersonaDAO.getInstancia().getPersonas();
		List<InquilinoEntity> personasInq = new ArrayList<InquilinoEntity>();

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		for(Persona p : personas) {
		personasInq = (List<InquilinoEntity>) s.createQuery("select i from InquilinoEntity i inner join i.persona").list();		
		}
		s.getTransaction().commit();
		
		for(InquilinoEntity pe : personasInq)
			resultado.add(PersonatoNegocio(pe));
		s.close();
		
		return resultado;
	}
	
public Unidad getUnidadPorInquilinoId(int id) throws PersonaException{
		
		InquilinoEntity personaInq = new InquilinoEntity();
		UnidadEntity rdo = new UnidadEntity();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		personaInq = (InquilinoEntity) s.createQuery("select i from InquilinoEntity i where i.id = ?").setInteger(0, id).uniqueResult();
		if(personaInq == null) {
			throw new PersonaException("No existe el inquilino " + id);
		}
		rdo = (UnidadEntity) s.createQuery("select u from UnidadEntity u where u.id = ?").setInteger(0, personaInq.getUnidad().getId()).uniqueResult();
		s.getTransaction().commit();		
		s.close();
		
		return UnidadDAO.getInstancia().toNegocio(rdo);
	}
	
	public Persona findById(int id) throws PersonaException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		InquilinoEntity inquilino = (InquilinoEntity) s.createQuery("from InquilinoEntity i where i.id = ? ")
				.setInteger(0, id).uniqueResult();
		if(inquilino == null)
			throw new PersonaException("No existe el inquilino " + id);
		return PersonatoNegocio(inquilino);
	}
	
	public List<Persona> findByIdentificador(int id) throws PersonaException {
		List<Persona> inquilinos = new ArrayList<Persona>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
			List<InquilinoEntity> inquilinosEntity = s.createQuery("from InquilinoEntity i where i.unidad.id = ? ")
					.setInteger(0, id).list();
			if(inquilinosEntity == null)
			{
				throw new PersonaException("No existen inquilinos.");
			}
			else 
			{
				for(InquilinoEntity i: inquilinosEntity) 
				{
					Persona per = PersonatoNegocio(i);
					inquilinos.add(per);
				}
			}
		return inquilinos;
	}


	public void save(Unidad unidad, Persona inquilino){
		InquilinoEntity aGrabar = toEntity(unidad, inquilino);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Unidad unidad, Persona inquilino){
		InquilinoEntity aGrabar = toEntity(unidad, inquilino);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	private InquilinoEntity toEntity(Unidad unidad, Persona persona){ 
		return new InquilinoEntity(UnidadDAO.getInstancia().toEntity(unidad),PersonaDAO.getInstancia().toEntity(persona));
	} 
	
	private Persona PersonatoNegocio(InquilinoEntity entity){
		return new Persona (entity.getPersona().getDni(), entity.getPersona().getNombre());
	}
	
}
	