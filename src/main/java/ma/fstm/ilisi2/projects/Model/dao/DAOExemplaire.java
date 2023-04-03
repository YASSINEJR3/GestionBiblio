/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.fstm.ilisi2.projects.Model.dao;

import java.util.List;

import ma.fstm.ilisi2.projects.Model.bo.Exemplaire;
import org.hibernate.*;
import org.hibernate.query.Query;



public class DAOExemplaire {
    
    public static boolean create(Exemplaire E){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
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
    
    
    public static boolean delete(Exemplaire E){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            tx = sc.beginTransaction();
            Exemplaire E1 = sc.get(Exemplaire.class,E.getIdExemp());
            sc.delete(E1);
            tx.commit();
            return true;
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return false;
        }
    }
    
    
    
    public static Exemplaire retreive(String isbn){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            Exemplaire E = null;
            try {
                tx = sc.beginTransaction();
                Query query = sc.createQuery("FROM Exemplaire E1 where E1.livre.isbn= :isbnValue and E1.idExemp not in (SELECT e.idExemp FROM Exemplaire e JOIN e.emprunts m WHERE e.livre.isbn= :isbnValue and m.status = :statusValue)");
                query.setParameter("isbnValue", isbn);
                query.setParameter("statusValue", 0);
                List<Exemplaire> exemplaires = query.list();
                if (exemplaires.size() != 0)
                    E = exemplaires.get(0);
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return E;
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
    }
    public static List<Exemplaire> getExemplairesDispo(String isbn){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            Exemplaire E = null;
            try {
                tx = sc.beginTransaction();
                Query query = sc.createQuery("FROM Exemplaire E1 where E1.livre.isbn= :isbnValue and E1.idExemp not in (SELECT e.idExemp FROM Exemplaire e JOIN e.emprunts m WHERE e.livre.isbn= :isbnValue and m.status = :statusValue)");
                query.setParameter("isbnValue", isbn);
                query.setParameter("statusValue", 0);
                List<Exemplaire> exemplaires = query.list();
                tx.commit();
                return exemplaires;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
        return null;
    }

    
    public static int NbrExemplaireEmpruntee(String isbn){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            try {
                tx = sc.beginTransaction();
                Query query = sc.createQuery("FROM Exemplaire E1 where E1.livre.isbn= :isbnValue and E1.idExemp not in (SELECT e.idExemp FROM Exemplaire e JOIN e.emprunts m WHERE e.livre.isbn = :isbnValue)");
                query.setParameter("isbnValue", isbn);
                List<Exemplaire> exemplaires = query.getResultList();
                tx.commit();
                return exemplaires.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(HibernateException e){
            tx.rollback();
            System.err.println(e);
            return 0;
        }
        return 0;
    }



}
