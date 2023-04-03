package ma.fstm.ilisi2.projects.Model.service;

import ma.fstm.ilisi2.projects.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.Model.bo.Emprunt;
import ma.fstm.ilisi2.projects.Model.bo.Exemplaire;
import ma.fstm.ilisi2.projects.Model.bo.Livre;
import ma.fstm.ilisi2.projects.Model.dao.DAOEmprunt;
import ma.fstm.ilisi2.projects.Model.dao.DAOExemplaire;
import ma.fstm.ilisi2.projects.Model.dao.DAOLivre;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ServiceEmprunt {
    public static ArrayList<Emprunt> getEmprunts() {
        return DAOEmprunt.retrieve();
    }

    public static ArrayList<Emprunt> getEmpruntsByCin(String s_cin) {
        if(!s_cin.isEmpty())
            return DAOEmprunt.getAdherentEmprunts(s_cin);
        else
            return DAOEmprunt.retrieve();
    }

    public static boolean ajouterEmprunt(String cin,String isbn) {

        if(cin.isEmpty() || isbn.isEmpty())
            return false;

        Adherent adherent = ServiceAdherent.getAdherentByCin(cin);
        Exemplaire exemplaire = DAOExemplaire.retreive(isbn);

        if(adherent == null || exemplaire == null)
            return false;

        Livre L = DAOLivre.findLivreByIsbn(isbn);
        if((L == null) || (L.getNbrexemp() == 0) )
            return false;

        if(!DAOEmprunt.create(new Emprunt(adherent,exemplaire)))
            return false;
        L.setNbrexemp(L.getNbrexemp() -1);
        DAOLivre.update(L);
        return true;
    }

    public static void retournerExemplaire(Integer id_emp,String isbn) {
        Emprunt emprunt = DAOEmprunt.getEmpruntById(id_emp);
        if(emprunt == null)
            return;
            
        if(emprunt.getStatus() == -1 || emprunt.getStatus() == 1)
            return;

        LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        if(emprunt.getDateRetour().compareTo(currentDate) < 0)
            emprunt.setStatus(-1);
        else
            emprunt.setStatus(1);

        DAOEmprunt.update(emprunt);
        Livre L = DAOLivre.findLivreByIsbn(isbn);
        L.setNbrexemp(L.getNbrexemp() + 1);
        DAOLivre.update(L);
    }
}
