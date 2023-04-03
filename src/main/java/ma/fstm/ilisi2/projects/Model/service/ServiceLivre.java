package ma.fstm.ilisi2.projects.Model.service;

import ma.fstm.ilisi2.projects.Model.bo.Exemplaire;
import ma.fstm.ilisi2.projects.Model.bo.Livre;
import ma.fstm.ilisi2.projects.Model.dao.DAOExemplaire;
import ma.fstm.ilisi2.projects.Model.dao.DAOLivre;

import java.util.ArrayList;
import java.util.List;

public class ServiceLivre {

    public static boolean ajouterLivre(String isbn, String title,String auteur ,Integer nbrexemp ){

        if(isbn.isEmpty() || title.isEmpty() || auteur.isEmpty() || nbrexemp < 0)
            return false;

        Livre L = new Livre(isbn, title,auteur,nbrexemp);
        if(DAOLivre.create(L)) {
            for (int i = 0; i < L.getNbrexemp(); i++)
                DAOExemplaire.create(new Exemplaire(L));

            return true;
        }
        return false;

    }

    public static boolean supprimerLivre(String isbn){

        if(isbn.isEmpty())
            return false;

        Livre L = DAOLivre.findLivreByIsbn(isbn);
        //delete exemplaires
        System.out.println(L.getExemplaires().size() + " " + DAOExemplaire.NbrExemplaireEmpruntee(isbn));

        List<Exemplaire> exemplairesDispo = DAOExemplaire.getExemplairesDispo(isbn);
        if(exemplairesDispo == null)
            return false;

        Integer nbrexempDispo = exemplairesDispo.size();
        if(L.getNbrexemp() == nbrexempDispo  && DAOExemplaire.NbrExemplaireEmpruntee(isbn) == 0)
        {
            for (Object e : L.getExemplaires())
                DAOExemplaire.delete((Exemplaire) e);

            return DAOLivre.delete(L);
        }
        return false;
    }

    public static boolean modifierLivre(String isbn, String title, String auteur, Integer nbrexemp ){

        if(isbn.isEmpty() || title.isEmpty() || auteur.isEmpty() || nbrexemp < 0)
            return false;

        return DAOLivre.update(new Livre(isbn,title,auteur,nbrexemp));
    }

    public static ArrayList<Livre> getAuteurLivres(String auteur){
        if(!auteur.isEmpty())
            return (ArrayList<Livre>) DAOLivre.getAuteurLivres(auteur);
        else
            return (ArrayList<Livre>) DAOLivre.retrieve();
    }

    public static ArrayList<Livre> getLivres(){
        return (ArrayList<Livre>) DAOLivre.retrieve();
    }

}
