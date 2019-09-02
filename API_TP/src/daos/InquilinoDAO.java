package daos;

import java.util.ArrayList;
import java.util.List;

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
		/*List<PersonaView> personas = Controlador.getInstancia().getPersonas();
		List<PersonaView> inquilinosView = new ArrayList<PersonaView>();
		
		for(PersonaView p : personas)
		{
			Session s = HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			List<InquilinoEntity> inquilino = s.createQuery("from InquilinoEntity i where i.documento = ?")
					.setString(0, p.getDocumento())
					.list();
			s.getTransaction().commit();
			s.close();
			
			if(inquilino != null)
			{
				inquilinosView.add(p);
			}
		}
		
		return inquilinosView;*/
		List<Persona> resultado = new ArrayList<Persona>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<PersonaEntity> personas = s.createQuery("select persona from InquilinoEntity i join PersonaEntity").list();
		s.getTransaction().commit();
		s.close();
		for(PersonaEntity pe : personas)
			resultado.add(toNegocio(pe));
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
	private Persona toNegocio(PersonaEntity entity){
		return new Persona (entity.getDni(), entity.getNombre());
	}
	
}
	