package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.UnidadEntity;
import exceptions.UnidadException;
import hibernate.HibernateUtil;
import modelo.Unidad;

public class UnidadDAO {
	
	private static UnidadDAO instancia;
		
	public static UnidadDAO getInstancia() {
		if(instancia == null)
			instancia = new UnidadDAO();
		return instancia;
	}
	
	public List<Unidad> getUnidades(){
		List<Unidad> resultado = new ArrayList<Unidad>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<UnidadEntity> unidades = s.createQuery("from UnidadEntity").list();
		s.getTransaction().commit();
		s.close();
		for(UnidadEntity ue : unidades)
			resultado.add(toNegocio(ue));
		return resultado;
	}
	
	public Unidad findById(int id) throws UnidadException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UnidadEntity unidad = (UnidadEntity) s.createQuery("from UnidadEntity u where u.id = ? ")
				.setInteger(0, id)
				.uniqueResult();
		if(unidad == null)
			throw new UnidadException("No existe la unidad " + id);
		return toNegocio(unidad);
	}
	
	public Unidad find(int codigo, String piso, String numero) throws UnidadException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UnidadEntity unidad = (UnidadEntity) s.createQuery("from UnidadEntity u where u.edificio.codigo = ? and u.piso = ? and u.numero = ? ")
				.setInteger(0, codigo)
				.setString(1, piso)
				.setString(2, numero)
				.uniqueResult();
		if(unidad == null)
			throw new UnidadException("No existe la unidad ");
		return toNegocio(unidad);
	}

	public List<Unidad> findByEdificio(int codigoEdificio) throws UnidadException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<UnidadEntity> unidades = s.createQuery("from UnidadEntity u where u.edificio.codigo = ? ")				
				.setInteger(0, codigoEdificio)
				.list();
		if(unidades == null)
			throw new UnidadException("El edificio no tiene unidades");
		
		List<Unidad> unidadesNegocio = new ArrayList<Unidad>();
		for(UnidadEntity ue : unidades)
		{
			unidadesNegocio.add(toNegocio(ue));
		}
		return unidadesNegocio;
	}


	public void save(Unidad unidad){
		UnidadEntity aGrabar = toEntity(unidad);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Unidad unidad){
		UnidadEntity aGrabar = toEntity(unidad);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	UnidadEntity toEntity(Unidad unidad){
		return new UnidadEntity(unidad.getId(), unidad.getPiso(), unidad.getNumero(), EdificioDAO.getInstancia().toEntity(unidad.getEdificio()), unidad.estaHabitado());
	} 
	
	Unidad toNegocio(UnidadEntity entity){
		return new Unidad(entity.getId(), entity.getPiso(), entity.getNumero(), EdificioDAO.getInstancia().toNegocio(entity.getEdificio()), entity.isHabitado());	
		}
	}
