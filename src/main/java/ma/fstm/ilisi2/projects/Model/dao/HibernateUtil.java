package ma.fstm.ilisi2.projects.Model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory entityManagerFactory;
    private static final SessionFactory sessionFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("oracle_db");
            sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }



}


