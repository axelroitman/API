package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Reclamo;

public class ReclamoDAO {
	
	private static ReclamoDAO instancia;
	
	public static ReclamoDAO getInstancia() {
		if(instancia == null)
			instancia = new ReclamoDAO();
		return instancia;
	}
	
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
		return new ReclamoEntity(PersonaDAO.getInstancia().toEntity(reclamo.getUsuario()), EdificioDAO.getInstancia().toEntity(reclamo.getEdificio()), reclamo.getUbicación(), reclamo.getDescripcion(), UnidadDAO.getInstancia().toEntity(reclamo.getUnidad()), reclamo.getEstado());
	} 
	
	Reclamo toNegocio(ReclamoEntity entity){
		return new Reclamo(PersonaDAO.getInstancia().toNegocio(entity.getUsuario()), EdificioDAO.getInstancia().toNegocio(entity.getEdificio()), entity.getUbicación(), entity.getDescripcion(), UnidadDAO.getInstancia().toNegocio(entity.getUnidad()));
	}
}
