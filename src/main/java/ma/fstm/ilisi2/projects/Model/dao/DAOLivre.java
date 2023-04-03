/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi2.projects.Model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import ma.fstm.ilisi2.projects.Model.bo.Exemplaire;
import ma.fstm.ilisi2.projects.Model.bo.Livre;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;


/**
 *
 * @author Dell
 */
public class DAOLivre {

    public static boolean create(Livre L) {
        Transaction tx = null;
        try{
            Session session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(L);
            tx.commit();
            return true;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            tx.rollback();
            return false;
        }
    }

    public static Collection<Livre> retrieve() {

        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            ArrayList<Livre> L = null;
            tx = sc.beginTransaction();
            L = new ArrayList<Livre>(sc.createQuery ("from Livre").list());
            tx.commit();
            return L;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
        
    }


    public static boolean update(Livre L) {
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            tx = sc.beginTransaction();
            sc.saveOrUpdate(L);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return false;
        }
    }


    public static boolean delete(Livre L) {
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            tx = sc.beginTransaction();
            Livre L1 = sc.get(Livre.class,L.getIsbn());
            sc.delete(L1);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return false;
        }
    }

    public static Livre findLivreByIsbn(String isbn){
        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            Livre L = null;
            try {
                tx = sc.beginTransaction();
                L = (Livre) sc.get(Livre.class, isbn);
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return L;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return null;
        }
    }

    public static Collection<Livre> getAuteurLivres(String auteur) {

        Transaction tx = null;
        try{
            Session sc = HibernateUtil.getSession();
            ArrayList<Livre> L = null;
            tx = sc.beginTransaction();
            Query query = sc.createQuery ("from Livre L where lower(L.auteur) =:auteur");
            query.setParameter("auteur",auteur.toLowerCase());
            L = new ArrayList<Livre>(query.getResultList());

            tx.commit();
            return L;
        }catch(Exception e){
            tx.rollback();
            System.err.println(e);
            return null;
        }

    }
}
