package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

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
             /*config.addAnnotatedClass(Usuario.class);
             config.addAnnotatedClass(Telefono.class);
             config.addAnnotatedClass(Domicilio.class);
             config.addAnnotatedClass(CajaAhorro.class);
             config.addAnnotatedClass(CuentaCorriente.class);*/
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
