package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // SessionFactory Hibernate unique
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Crée la SessionFactory à partir du fichier hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("✅ SessionFactory Hibernate initialisée !");
        } catch (Throwable ex) {
            System.err.println("❌ Échec de création de la SessionFactory : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Méthode pour obtenir la SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Méthode pour fermer Hibernate proprement
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }}