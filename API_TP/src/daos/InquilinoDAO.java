package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import controlador.Controlador;
import entities.InquilinoEntity;
import entities.PersonaEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Persona;
import views.PersonaView;

public class InquilinoDAO {
	public List<Persona> getInquilinos(){
		
		List<Persona> resultado = new ArrayList<Persona>();
		List<Persona> personas = new PersonaDAO().getPersonas();
		List<InquilinoEntity> personasInq = new ArrayList<InquilinoEntity>();

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		for(Persona p : personas) {
		personasInq = (List<InquilinoEntity>) s.createQuery("select i from InquilinoEntity i inner join i.persona").list();		
		}
		s.getTransaction().commit();
		
		for(InquilinoEntity pe : personasInq)
			resultado.add(toNegocio(pe));
		s.close();
		
		return resultado;
	}
	/*
	public PersonaView findById(int id) throws PersonaException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		PersonaEntity inquilino = (InquilinoEntity) s.createQuery("from InquilinoEntity i where i.id = ? ")
				.setInteger(0, i)
				.uniqueResult();
		if(inquilino == null)
			throw new InquilinoException("No existe el inquilino " + id);
		return toNegocio(inquilino);
	}

	public void save(PersonaView persona){
		InquilinoEntity aGrabar = toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Persona persona){
		PersonaEntity aGrabar = toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	private PersonaEntity toEntity(Persona persona){
		return new PersonaEntity(persona.getDocumento(), persona.getNombre());
	} 
	*/
	private Persona toNegocio(InquilinoEntity entity){
		return new Persona (entity.getPersona().getDni(), entity.getPersona().getNombre());
	}
	
}
	