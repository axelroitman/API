package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.ImagenEntity;
import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Reclamo;

public class ImagenDAO {
	private static ImagenDAO instancia;
	
	public static ImagenDAO getInstancia() {
		if(instancia == null)
			instancia = new ImagenDAO();
		return instancia;
	}
	
	public List<Imagen> getImagenes(){
			
			List<Imagen> resultado = new ArrayList<Imagen>();
			List<ImagenEntity> imagenes = new ArrayList<ImagenEntity>();
	
			Session s = HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			imagenes = (List<ImagenEntity>) s.createQuery("from ImagenEntity").list();
			s.getTransaction().commit();
			
			for(ImagenEntity im : imagenes)
				resultado.add(toNegocio(im));
			
			s.close();
			
			return resultado;
		}
	
	public List<Imagen> getImagenesOfReclamo(int idReclamo){
		
		List<Imagen> resultado = new ArrayList<Imagen>();
		List<ImagenEntity> imagenes = new ArrayList<ImagenEntity>();

		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		imagenes = (List<ImagenEntity>) s.createQuery("from ImagenEntity ie where ie.reclamo = ?").setInteger(0, idReclamo).list();
		s.getTransaction().commit();
		
		for(ImagenEntity im : imagenes)
			resultado.add(toNegocio(im));
		
		s.close();
		
		return resultado;
	}
	
	public void save(Imagen im, int idReclamo){
		ImagenEntity aGrabar = toEntity(im, idReclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	public void update(Imagen im, int idReclamo){
		ImagenEntity aGrabar = toEntity(im, idReclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGrabar);
		s.getTransaction().commit();
		s.close();
	}
	
	private Imagen toNegocio(ImagenEntity entity) {
		return new Imagen(entity.getPath(), entity.getTipo());
	}
	
	private ImagenEntity toEntity(Imagen im, int idReclamo) {
		Reclamo reclamo = null;
		try {
			reclamo = ReclamoDAO.getInstancia().findById(idReclamo);
		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReclamoEntity re = ReclamoDAO.getInstancia().toEntity(reclamo);
		return new ImagenEntity(im.getNumero(), im.getDireccion(), re);
	}


}
