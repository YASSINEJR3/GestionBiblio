package ma.fstm.ilisi2.projects.Model.bo;



import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name="LIVRE"
    ,schema="HR"
)
public class Livre  implements java.io.Serializable {


     private String isbn;
     private String titre;
     private Integer nbrexemp;
     private String auteur;
     private Set<Exemplaire> exemplaires = new HashSet<>(0);

    public Livre() {
    }

	
    public Livre(String isbn) {
        this.isbn = isbn;
    }
    public Livre(String isbn, String titre, String auteur,Integer nbrexemp) {
       this.isbn = isbn;
       this.titre = titre;
       this.nbrexemp = nbrexemp;
       this.auteur = auteur;
    }
    public Livre(String isbn, String titre, Integer nbrexemp, String auteur, Set<Exemplaire> exemplaires) {
       this.isbn = isbn;
       this.titre = titre;
       this.nbrexemp = nbrexemp;
       this.auteur = auteur;
       this.exemplaires = exemplaires;
    }

   
    @Id
    @Column(name="ISBN", unique=true, nullable=false, length=20)
    public String getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
    @Column(name="TITRE", length=20)
    public String getTitre() {
        return this.titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    @Column(name="NBREXEMP", precision=22, scale=0)
    public Integer getNbrexemp() {
        return this.nbrexemp;
    }
    
    public void setNbrexemp(Integer nbrexemp) {
        this.nbrexemp = nbrexemp;
    }

    
    @Column(name="AUTEUR", length=20)
    public String getAuteur() {
        return this.auteur;
    }
    
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="livre")
    public Set<Exemplaire> getExemplaires() {
        return this.exemplaires;
    }
    
    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }


    @Override
    public String toString() {
        return "Livre{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", nbrexemp=" + nbrexemp +
                ", auteur='" + auteur + '\'' +
                ", exemplaires=" + exemplaires +
                '}';
    }
}


