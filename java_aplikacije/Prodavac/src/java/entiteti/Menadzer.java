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
@Table(name = "menadzer")
@NamedQueries({
    @NamedQuery(name = "Menadzer.findAll", query = "SELECT m FROM Menadzer m")
    , @NamedQuery(name = "Menadzer.findByIdMenadzer", query = "SELECT m FROM Menadzer m WHERE m.idMenadzer = :idMenadzer")
    , @NamedQuery(name = "Menadzer.findByIme", query = "SELECT m FROM Menadzer m WHERE m.ime = :ime")
    , @NamedQuery(name = "Menadzer.findByPrezime", query = "SELECT m FROM Menadzer m WHERE m.prezime = :prezime")})
public class Menadzer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMenadzer")
    private Integer idMenadzer;
    @Size(max = 45)
    @Column(name = "Ime")
    private String ime;
    @Size(max = 45)
    @Column(name = "Prezime")
    private String prezime;
    @JoinColumn(name = "idProdavnica", referencedColumnName = "idProdavnica")
    @ManyToOne(optional = false)
    private Prodavnica idProdavnica;

    public Menadzer() {
    }

    public Menadzer(Integer idMenadzer) {
        this.idMenadzer = idMenadzer;
    }

    public Integer getIdMenadzer() {
        return idMenadzer;
    }

    public void setIdMenadzer(Integer idMenadzer) {
        this.idMenadzer = idMenadzer;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Prodavnica getIdProdavnica() {
        return idProdavnica;
    }

    public void setIdProdavnica(Prodavnica idProdavnica) {
        this.idProdavnica = idProdavnica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenadzer != null ? idMenadzer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menadzer)) {
            return false;
        }
        Menadzer other = (Menadzer) object;
        if ((this.idMenadzer == null && other.idMenadzer != null) || (this.idMenadzer != null && !this.idMenadzer.equals(other.idMenadzer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Menadzer[ idMenadzer=" + idMenadzer + " ]";
    }
    
}
