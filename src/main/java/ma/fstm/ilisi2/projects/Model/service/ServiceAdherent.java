package ma.fstm.ilisi2.projects.Model.service;

import ma.fstm.ilisi2.projects.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.Model.dao.DAOAdherent;

import java.util.ArrayList;

public class ServiceAdherent {


    public static ArrayList<Adherent> getAdherents() {
        return (ArrayList<Adherent>) DAOAdherent.retrieve();
    }

    public static boolean supprimerAdherent(Adherent adherent) {
        return DAOAdherent.delete(adherent);
    }

    public static void modifierAdherent(Adherent adherent) {
        DAOAdherent.update(adherent);
    }

    public static Adherent getAdherentByCin(String s_cin) {
        return DAOAdherent.getAdherentByCin(s_cin);
    }

    public static boolean ajouterAdherent(Adherent adherent) {

        if(adherent.getCin().isEmpty())
            return false;

        if(DAOAdherent.getAdherentByCin(adherent.getCin()) != null)
            return false;

        return DAOAdherent.create(adherent);
    }
}
