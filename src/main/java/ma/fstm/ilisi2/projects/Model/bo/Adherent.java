package ma.fstm.ilisi2.projects.Model.bo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Adherent" ,schema = "HR")
public class Adherent  implements Serializable {

     @Id
     @Column(name="ID_CLIENT", unique=true, nullable=false, precision=22, scale=0)
     @GeneratedValue(generator="CLIENT_SEQ")
     @SequenceGenerator(name="CLIENT_SEQ",sequenceName="CLIENT_SEQ",allocationSize = 1)
     private Integer idClient;
     private String nom;
     private String prenom;
     private String cin;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="adherent")
    private Set<Emprunt> emprunts = new HashSet(0);

    public Adherent() {
    }

    public Adherent(Integer idClient, String nom, String prenom, String cin) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
    }

    public Adherent(Integer idClient) {
        this.idClient = idClient;
    }

    public Adherent(String nom, String prenom, String cin) {
       this.nom = nom;
       this.prenom = prenom;
       this.cin = cin;
    }
    
    public Adherent(String nom, String prenom, String cin, Set<Emprunt> emprunts) {
       this.nom = nom;
       this.prenom = prenom;
       this.cin = cin;
       this.emprunts = emprunts;
    }



    public Integer getIdClient() {
        return this.idClient;
    }
    
    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getCin() {
        return this.cin;
    }
    
    public void setCin(String cin) {
        this.cin = cin;
    }
    public Set<Emprunt> getEmprunts() {
        return this.emprunts;
    }
    
    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
    public Integer getNbremprunt(){
        return emprunts.size();
    }


    @Override
    public String toString() {
        return "Adherent{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", emprunts=" + emprunts +
                '}';
    }
}


