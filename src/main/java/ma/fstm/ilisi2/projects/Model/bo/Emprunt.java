package ma.fstm.ilisi2.projects.Model.bo;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="EMPRUNT"
        ,schema="HR"
)
public class Emprunt  implements Serializable {

     @Id
     @Column(name="ID_EMP", unique=true, nullable=false, precision=22, scale=0)
     @GeneratedValue(generator="EMPRUNT_SEQ")
     @SequenceGenerator(name="EMPRUNT_SEQ",sequenceName="EMPRUNT_SEQ",allocationSize = 1)
     private Integer idEmp;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_EXEMP", nullable=false)
     private Exemplaire exemplaire;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_CLIENT", nullable=false)
     private Adherent adherent;
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_EMP", nullable=false, length=7)
    private Date dateEmp;

    @Column(name="STATUS")
    private Integer status;

    @Transient
    private LocalDate dateRetour;
    public Emprunt() {
    }

    public Emprunt( Adherent adherent ,Exemplaire exemplaire) {
       this.exemplaire = exemplaire;
       this.adherent = adherent;
       this.dateEmp = new Date();
       this.status = 0;
    }


    public Integer getIdEmp() {
        return this.idEmp;
    }
    
    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }
    public Exemplaire getExemplaire() {
        return this.exemplaire;
    }
    
    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
    public Adherent getAdherent() {
        return this.adherent;
    }
    
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    public Date getDateEmp() {
        return this.dateEmp;
    }
    
    public void setDateEmp(Date dateEmp) {
        this.dateEmp = dateEmp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    @PostLoad
    public void setDateRetour() {
        this.dateRetour = LocalDate.parse(this.dateEmp.toString()).plusDays(15);
    }
}


