package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.EdificioEntity;
import entities.InquilinoEntity;
import entities.PersonaEntity;
import entities.UnidadEntity;

/*import bean.CajaAhorro;
import bean.CuentaCorriente;
import bean.Domicilio;
import bean.Telefono;
import bean.Usuario;*/
 
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(EdificioEntity.class);
             config.addAnnotatedClass(InquilinoEntity.class);
             config.addAnnotatedClass(UnidadEntity.class);
             config.addAnnotatedClass(PersonaEntity.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
