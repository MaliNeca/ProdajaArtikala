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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nemanja
 */
@Entity
@Table(name = "rezervacija")
@NamedQueries({
    @NamedQuery(name = "Rezervacija.findAll", query = "SELECT r FROM Rezervacija r")
    , @NamedQuery(name = "Rezervacija.findByIdRezervacija", query = "SELECT r FROM Rezervacija r WHERE r.idRezervacija = :idRezervacija")
    , @NamedQuery(name = "Rezervacija.findByIme", query = "SELECT r FROM Rezervacija r WHERE r.ime = :ime")
    , @NamedQuery(name = "Rezervacija.findByPrezime", query = "SELECT r FROM Rezervacija r WHERE r.prezime = :prezime")
    , @NamedQuery(name = "Rezervacija.findByJmbg", query = "SELECT r FROM Rezervacija r WHERE r.jmbg = :jmbg")
    , @NamedQuery(name = "Rezervacija.findByKolicina", query = "SELECT r FROM Rezervacija r WHERE r.kolicina = :kolicina")
    , @NamedQuery(name = "Rezervacija.findByIstek", query = "SELECT r FROM Rezervacija r WHERE r.istek = :istek")
    , @NamedQuery(name = "Rezervacija.findByKontakt", query = "SELECT r FROM Rezervacija r WHERE r.kontakt = :kontakt")
    , @NamedQuery(name = "Rezervacija.findByMestoPodizanja", query = "SELECT r FROM Rezervacija r WHERE r.mestoPodizanja = :mestoPodizanja")})
public class Rezervacija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRezervacija")
    private Integer idRezervacija;
    @Size(max = 64)
    @Column(name = "Ime")
    private String ime;
    @Size(max = 64)
    @Column(name = "Prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "JMBG")
    private String jmbg;
    @Column(name = "Kolicina")
    private Integer kolicina;
    @Size(max = 30)
    @Column(name = "Istek")
    private String istek;
    @Size(max = 45)
    @Column(name = "Kontakt")
    private String kontakt;
    @Size(max = 45)
    @Column(name = "MestoPodizanja")
    private String mestoPodizanja;
    @JoinColumn(name = "fk_idArtikal", referencedColumnName = "idArtikal")
    @ManyToOne(optional = false)
    private Artikal fkidArtikal;
    @JoinColumn(name = "fk_idProdavnica", referencedColumnName = "idProdavnica")
    @ManyToOne(optional = false)
    private Prodavnica fkidProdavnica;

    public Rezervacija() {
    }

    public Rezervacija(Integer idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public Rezervacija(Integer idRezervacija, String jmbg) {
        this.idRezervacija = idRezervacija;
        this.jmbg = jmbg;
    }

    public Integer getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(Integer idRezervacija) {
        this.idRezervacija = idRezervacija;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getIstek() {
        return istek;
    }

    public void setIstek(String istek) {
        this.istek = istek;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getMestoPodizanja() {
        return mestoPodizanja;
    }

    public void setMestoPodizanja(String mestoPodizanja) {
        this.mestoPodizanja = mestoPodizanja;
    }

    public Artikal getFkidArtikal() {
        return fkidArtikal;
    }

    public void setFkidArtikal(Artikal fkidArtikal) {
        this.fkidArtikal = fkidArtikal;
    }

    public Prodavnica getFkidProdavnica() {
        return fkidProdavnica;
    }

    public void setFkidProdavnica(Prodavnica fkidProdavnica) {
        this.fkidProdavnica = fkidProdavnica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRezervacija != null ? idRezervacija.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.idRezervacija == null && other.idRezervacija != null) || (this.idRezervacija != null && !this.idRezervacija.equals(other.idRezervacija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Rezervacija[ idRezervacija=" + idRezervacija + " ]";
    }
    
}
