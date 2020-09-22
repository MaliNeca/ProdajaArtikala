/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Nemanja
 */
@Entity
@Table(name = "promet")
@NamedQueries({
    @NamedQuery(name = "Promet.findAll", query = "SELECT p FROM Promet p")
    , @NamedQuery(name = "Promet.findByIdPromet", query = "SELECT p FROM Promet p WHERE p.idPromet = :idPromet")
    , @NamedQuery(name = "Promet.findByBrojProdatih", query = "SELECT p FROM Promet p WHERE p.brojProdatih = :brojProdatih")
    , @NamedQuery(name = "Promet.findByDatum", query = "SELECT p FROM Promet p WHERE p.datum = :datum")
    , @NamedQuery(name = "Promet.findByIznos", query = "SELECT p FROM Promet p WHERE p.iznos = :iznos")})
public class Promet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPromet")
    private Integer idPromet;
    @Column(name = "BrojProdatih")
    private Integer brojProdatih;
    @Size(max = 15)
    @Column(name = "Datum")
    private String datum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Iznos")
    private Double iznos;
    @JoinColumn(name = "fk_prodavnica", referencedColumnName = "idProdavnica")
    @ManyToOne(optional = false)
    private Prodavnica fkProdavnica;

    public Promet() {
    }

    public Promet(Integer idPromet) {
        this.idPromet = idPromet;
    }

    public Integer getIdPromet() {
        return idPromet;
    }

    public void setIdPromet(Integer idPromet) {
        this.idPromet = idPromet;
    }

    public Integer getBrojProdatih() {
        return brojProdatih;
    }

    public void setBrojProdatih(Integer brojProdatih) {
        this.brojProdatih = brojProdatih;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Prodavnica getFkProdavnica() {
        return fkProdavnica;
    }

    public void setFkProdavnica(Prodavnica fkProdavnica) {
        this.fkProdavnica = fkProdavnica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromet != null ? idPromet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promet)) {
            return false;
        }
        Promet other = (Promet) object;
        if ((this.idPromet == null && other.idPromet != null) || (this.idPromet != null && !this.idPromet.equals(other.idPromet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Promet[ idPromet=" + idPromet + " ]";
    }
    
}
