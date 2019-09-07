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
	
	public PersonaView findById(int id) throws PersonaException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		InquilinoEntity inquilino = (InquilinoEntity) s.createQuery("from InquilinoEntity i where i.id = ? ")
				.setInteger(0, id)
				.uniqueResult();
		if(inquilino == null)
			throw new PersonaException("No existe el inquilino " + id);
		
		PersonaView inquilinoView = new PersonaView();
		inquilinoView = toNegocio(inquilino).toView();
		return inquilinoView;
	}

	public void save(PersonaView inquilino){
		PersonaEntity persona = new PersonaEntity(inquilino.getDocumento(), inquilino.getNombre());
		InquilinoEntity aGrabar = toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	

	public void update(PersonaView inquilino){
		PersonaEntity persona = new PersonaEntity(inquilino.getDocumento(), inquilino.getNombre());
		InquilinoEntity aGrabar = toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	/*toEntity se lleva PersonaEntity para que no rompa todo, 
	pero en otros DAOs se lleva siempre una view. 
	El tema es que InquilinoEntity se crea con un PersonaEntity, no con un PersonaView.
	Si está mal, corregir.*/
	private InquilinoEntity toEntity(PersonaEntity persona){ 
		
		return new InquilinoEntity(persona);
	} 
	
	private Persona toNegocio(InquilinoEntity entity){
		return new Persona (entity.getPersona().getDni(), entity.getPersona().getNombre());
	}
	
}
	