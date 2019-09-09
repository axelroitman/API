package daos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import entities.DuenioEntity;
import entities.InquilinoEntity;
import entities.PersonaEntity;
import exceptions.PersonaException;
import hibernate.HibernateUtil;
import modelo.Persona;
import views.PersonaView;

public class DuenioDAO {
	public List<Persona> getDuenios(){
		
		List<Persona> resultado = new ArrayList<Persona>();
		List<Persona> personas = new PersonaDAO().getPersonas();
		List<DuenioEntity> personasDuen = new ArrayList<DuenioEntity>();

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		for(Persona p : personas) {
		personasDuen = (List<DuenioEntity>) s.createQuery("select i from DuenioEntity i inner join i.persona").list();		
		}
		s.getTransaction().commit();
		
		for(DuenioEntity pe : personasDuen)
			resultado.add(toNegocio(pe));
		s.close();
		
		return resultado;
	}
	
	public PersonaView findById(int id) throws PersonaException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		DuenioEntity duenio = (DuenioEntity) s.createQuery("from DuenioEntity i where i.id = ? ")
				.setInteger(0, id).uniqueResult();
		if(duenio == null)
			throw new PersonaException("No existe el duenio " + id);
		
		PersonaView duenioView = new PersonaView();
		duenioView = toNegocio(duenio).toView();
		return duenioView;
	}

	public void save(PersonaView duenio){
		PersonaEntity persona = new PersonaEntity(duenio.getDocumento(), duenio.getNombre());
		DuenioEntity aGrabar = toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(PersonaView duenio){
		PersonaEntity persona = new PersonaEntity(duenio.getDocumento(), duenio.getNombre());
		DuenioEntity aGrabar = toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	/*toEntity se lleva PersonaEntity para que no rompa todo, 
	pero en otros DAOs se lleva siempre una view. 
	El tema es que InquilinoEntity se crea con un PersonaEntity, no con un PersonaView.
	Si esta mal, corregir.*/
	private DuenioEntity toEntity(PersonaEntity persona){ 
		return new DuenioEntity(persona);
	} 
	
	private Persona toNegocio(DuenioEntity entity){
		return new Persona (entity.getPersona().getDni(), entity.getPersona().getNombre());
	}
	
}