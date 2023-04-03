package ma.fstm.ilisi2.projects.Model.dao;

import ma.fstm.ilisi2.projects.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.Model.bo.Livre;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

public class DAOAdherent {
    public static boolean create(Adherent C) {
        Transaction tx = null;
        try{
            Session session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(C);
            tx.commit();
            return true;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            tx.rollback();
            return false;
        }
    }

    public static Collection<Adherent> retrieve() {

        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            ArrayList<Adherent> C = null;
            tx = sc.beginTransaction();
            C = new ArrayList<Adherent>(sc.createQuery ("from Adherent ").list());
            tx.commit();
            return C;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return null;
        }

    }


    public static boolean update(Adherent C) {
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            tx = sc.beginTransaction();
            sc.saveOrUpdate(C);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return false;
        }
    }


    public static boolean delete(Adherent C) {
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            tx = sc.beginTransaction();
            Adherent C1 = sc.get(Adherent.class,C.getIdClient());
            sc.delete(C1);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return false;
        }
    }

    public static Adherent findAdherentById(Integer id){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            Adherent C = null;
            try {
                tx = sc.beginTransaction();
                C = (Adherent) sc.get(Adherent.class, id);
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return C;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
    }

    public static Adherent getAdherentByCin(String s_cin) {
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            Adherent C = null;
            tx = sc.beginTransaction();
            Query query = sc.createQuery ("from Adherent A where lower(A.cin) =:cin");
            query.setParameter("cin",s_cin.toLowerCase());
            C = (Adherent) query.getSingleResult();
            tx.commit();
            return C;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
    }
}
