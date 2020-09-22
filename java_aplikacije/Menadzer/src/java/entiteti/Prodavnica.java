/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Nemanja
 */
@Entity
@Table(name = "prodavnica")
@NamedQueries({
    @NamedQuery(name = "Prodavnica.findAll", query = "SELECT p FROM Prodavnica p")
    , @NamedQuery(name = "Prodavnica.findByIdProdavnica", query = "SELECT p FROM Prodavnica p WHERE p.idProdavnica = :idProdavnica")
    , @NamedQuery(name = "Prodavnica.findByMesto", query = "SELECT p FROM Prodavnica p WHERE p.mesto = :mesto")
    , @NamedQuery(name = "Prodavnica.findByNaziv", query = "SELECT p FROM Prodavnica p WHERE p.naziv = :naziv")})
public class Prodavnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProdavnica")
    private Integer idProdavnica;
    @Size(max = 32)
    @Column(name = "Mesto")
    private String mesto;
    @Size(max = 32)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidProdavnica")
    private List<Rezervacija> rezervacijaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodavnica")
    private List<NaStanju> naStanjuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProdavnica")
    private List<Promet> prometList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProdavnica")
    private List<Menadzer> menadzerList;

    public Prodavnica() {
    }

    public Prodavnica(Integer idProdavnica) {
        this.idProdavnica = idProdavnica;
    }

    public Integer getIdProdavnica() {
        return idProdavnica;
    }

    public void setIdProdavnica(Integer idProdavnica) {
        this.idProdavnica = idProdavnica;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Rezervacija> getRezervacijaList() {
        return rezervacijaList;
    }

    public void setRezervacijaList(List<Rezervacija> rezervacijaList) {
        this.rezervacijaList = rezervacijaList;
    }

    public List<NaStanju> getNaStanjuList() {
        return naStanjuList;
    }

    public void setNaStanjuList(List<NaStanju> naStanjuList) {
        this.naStanjuList = naStanjuList;
    }

    public List<Promet> getPrometList() {
        return prometList;
    }

    public void setPrometList(List<Promet> prometList) {
        this.prometList = prometList;
    }

    public List<Menadzer> getMenadzerList() {
        return menadzerList;
    }

    public void setMenadzerList(List<Menadzer> menadzerList) {
        this.menadzerList = menadzerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProdavnica != null ? idProdavnica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prodavnica)) {
            return false;
        }
        Prodavnica other = (Prodavnica) object;
        if ((this.idProdavnica == null && other.idProdavnica != null) || (this.idProdavnica != null && !this.idProdavnica.equals(other.idProdavnica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Prodavnica[ idProdavnica=" + idProdavnica + " ]";
    }
    
}
