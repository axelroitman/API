package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.PersonaEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Persona;

public class PersonaDAO {
	
private static PersonaDAO instancia;
	
	public static PersonaDAO getInstancia() {
		if(instancia == null)
			instancia = new PersonaDAO();
		return instancia;
	}
	
	public List<Persona> getPersonas(){
		List<Persona> resultado = new ArrayList<Persona>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<PersonaEntity> personas = s.createQuery("from PersonaEntity").list();
		s.getTransaction().commit();
		s.close();
		for(PersonaEntity pe : personas)
			resultado.add(toNegocio(pe));
		return resultado;
	}
	
	public Persona findById(String dni) throws PersonaException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		PersonaEntity persona = (PersonaEntity) s.createQuery("select from PersonaEntity p where p.dni = ?")
				.setString(0, dni)
				.uniqueResult();
		if(persona == null)
			throw new PersonaException("No existe la persona " + dni);
		return toNegocio(persona);
	}

	public void save(Persona persona){
		PersonaEntity aGrabar = toEntity(persona);
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
	
	PersonaEntity toEntity(Persona persona){
		return new PersonaEntity(persona.getDocumento(), persona.getNombre());
	} 
	
	Persona toNegocio(PersonaEntity entity){
		return new Persona (entity.getDni(), entity.getNombre());
	}
	
	
}


