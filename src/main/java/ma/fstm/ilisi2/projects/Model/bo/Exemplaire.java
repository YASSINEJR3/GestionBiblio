package ma.fstm.ilisi2.projects.Model.bo;



import org.hibernate.annotations.GeneratorType;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name="EXEMPLAIRE"
    ,schema="HR"
)
public class Exemplaire  implements java.io.Serializable {


     private Integer idExemp;
     private Livre livre;
     private Set<Emprunt> emprunts = new HashSet<>(0);


    public Exemplaire() {
    }

    public Exemplaire(Livre livre) {
        this.livre = livre;
    }

    public Exemplaire(Livre livre, Set<Emprunt> emprunts) {
        this.livre = livre;
        this.emprunts = emprunts;
    }
   
     @Id
     @Column(name="ID_EXEMP", unique=true, nullable=false, precision=22, scale=0)
     @GeneratedValue(generator="EXEMPLAIRE_SEQ")
     @SequenceGenerator(name="EXEMPLAIRE_SEQ",sequenceName="EXEMPLAIRE_SEQ", allocationSize = 1)
    public Integer getIdExemp() {
        return this.idExemp;
    }
    
    public void setIdExemp(Integer idExemp) {
        this.idExemp = idExemp;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_LIVRE", nullable=false)
    public Livre getLivre() {
        return this.livre;
    }
    
    public void setLivre(Livre livre) {
        this.livre = livre;
    }


    @OneToMany(fetch=FetchType.LAZY, mappedBy="exemplaire")
    public Set<Emprunt> getEmprunts() {
        return this.emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }





}


