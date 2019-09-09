package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;


import entities.EdificioEntity;
import exceptions.EdificioException;
import hibernate.HibernateUtil;
import modelo.Edificio;

public class EdificioDAO {
	
	public List<Edificio> getEdificios(){
		List<Edificio> resultado = new ArrayList<Edificio>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<EdificioEntity> edificios = s.createQuery("from EdificioEntity").list();
		s.getTransaction().commit();
		s.close();
		for(EdificioEntity ee : edificios)
			resultado.add(toNegocio(ee));
		return resultado;
	}
	
	public Edificio findById(int numero) throws EdificioException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		EdificioEntity edificio = (EdificioEntity) s.createQuery("from EdificioEntity e where e.codigo = ? ")
				.setInteger(0, numero)
				.uniqueResult();
		if(edificio == null)
			throw new EdificioException("No existe el edificio " + numero);
		return toNegocio(edificio);
	}

	public void save(Edificio edificio){
		EdificioEntity aGrabar = toEntity(edificio);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Edificio edificio){
		EdificioEntity aGrabar = toEntity(edificio);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	EdificioEntity toEntity(Edificio edificio){
		return new EdificioEntity(edificio.getCodigo(), edificio.getNombre(), edificio.getDireccion());
	} 
	
	Edificio toNegocio(EdificioEntity entity){
		return new Edificio(entity.getCodigo(), entity.getNombre(), entity.getDireccion());
	}
	
	
}
