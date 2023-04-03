package ma.fstm.ilisi2.projects.Model.dao;


import ma.fstm.ilisi2.projects.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.Model.bo.Emprunt;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DAOEmprunt {
    
    public static boolean create(Emprunt E){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = sc.beginTransaction();
            sc.save(E);
            tx.commit();
            return true;
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return false;
        }
    }
    public static ArrayList<Emprunt> retrieve(){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSessionFactory().getCurrentSession();
            ArrayList<Emprunt> L = null;
            try {
                tx = sc.beginTransaction();
                L = new ArrayList<Emprunt>(sc.createQuery ("from Emprunt").list());
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return L;
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
    }

    public static ArrayList<Emprunt> getAdherentEmprunts(String cin){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSessionFactory().getCurrentSession();
            ArrayList<Emprunt> L = null;
            try {
                tx = sc.beginTransaction();
                Query query = sc.createQuery ("from Emprunt e where lower(e.adherent.cin) =:cin");
                query.setParameter("cin",cin.toLowerCase());
                L = new ArrayList<Emprunt>(query.getResultList());
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return L;
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
    }

    public static Emprunt getEmpruntById(Integer id_emp) {
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSessionFactory().getCurrentSession();
            //tx = sc.beginTransaction();
            Emprunt L = null;
            try {
                tx = sc.beginTransaction();
                Query query = sc.createQuery ("from Emprunt e where e.id =:id_emp");
                query.setParameter("id_emp",id_emp);
                L = (Emprunt) query.getSingleResult();
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return L;
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
    }

    public static void update(Emprunt emprunt) {
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = sc.beginTransaction();
            sc.update(emprunt);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
        }
    }
}
