package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Reclamo;

public class ReclamoDAO {
	
	public List<Reclamo> getReclamos(){
		List<Reclamo> resultado = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamos = s.createQuery("from ReclamoEntity").list();
		s.getTransaction().commit();
		s.close();
		for(ReclamoEntity rec : reclamos)
			resultado.add(toNegocio(rec));
		return resultado;
	}
	
	public Reclamo findById(int id) throws ReclamoException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		ReclamoEntity reclamo = (ReclamoEntity) s.createQuery("from ReclamoEntity r where r.id = ? ")
				.setInteger(0, id)
				.uniqueResult();
		if(reclamo == null)
			throw new ReclamoException("No existe el reclamo " + id);
		return toNegocio(reclamo);
	}
	
	public void save(Reclamo reclamo){
		ReclamoEntity aGrabar = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Reclamo reclamo){
		ReclamoEntity aGrabar = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	ReclamoEntity toEntity(Reclamo reclamo){
		return new ReclamoEntity(new PersonaDAO().toEntity(reclamo.getUsuario()), new EdificioDAO().toEntity(reclamo.getEdificio()), reclamo.getUbicación(), reclamo.getDescripcion(), new UnidadDAO().toEntity(reclamo.getUnidad()), reclamo.getEstado());
	} 
	
	Reclamo toNegocio(ReclamoEntity entity){
		return new Reclamo(new PersonaDAO().toNegocio(entity.getUsuario()), new EdificioDAO().toNegocio(entity.getEdificio()), entity.getUbicación(), entity.getDescripcion(), new UnidadDAO().toNegocio(entity.getUnidad()));
	}
}
