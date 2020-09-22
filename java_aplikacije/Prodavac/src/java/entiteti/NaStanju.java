/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nemanja
 */
@Entity
@Table(name = "na_stanju")
@NamedQueries({
    @NamedQuery(name = "NaStanju.findAll", query = "SELECT n FROM NaStanju n")
    , @NamedQuery(name = "NaStanju.findByKolicina", query = "SELECT n FROM NaStanju n WHERE n.kolicina = :kolicina")
    , @NamedQuery(name = "NaStanju.findByIdProdavnica", query = "SELECT n FROM NaStanju n WHERE n.naStanjuPK.idProdavnica = :idProdavnica")
    , @NamedQuery(name = "NaStanju.findByIdArtikal", query = "SELECT n FROM NaStanju n WHERE n.naStanjuPK.idArtikal = :idArtikal")})
public class NaStanju implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NaStanjuPK naStanjuPK;
    @Column(name = "Kolicina")
    private Integer kolicina;
    @JoinColumn(name = "idArtikal", referencedColumnName = "idArtikal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikal artikal;
    @JoinColumn(name = "idProdavnica", referencedColumnName = "idProdavnica", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prodavnica prodavnica;

    public NaStanju() {
    }

    public NaStanju(NaStanjuPK naStanjuPK) {
        this.naStanjuPK = naStanjuPK;
    }

    public NaStanju(int idProdavnica, int idArtikal) {
        this.naStanjuPK = new NaStanjuPK(idProdavnica, idArtikal);
    }

    public NaStanjuPK getNaStanjuPK() {
        return naStanjuPK;
    }

    public void setNaStanjuPK(NaStanjuPK naStanjuPK) {
        this.naStanjuPK = naStanjuPK;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Prodavnica getProdavnica() {
        return prodavnica;
    }

    public void setProdavnica(Prodavnica prodavnica) {
        this.prodavnica = prodavnica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (naStanjuPK != null ? naStanjuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NaStanju)) {
            return false;
        }
        NaStanju other = (NaStanju) object;
        if ((this.naStanjuPK == null && other.naStanjuPK != null) || (this.naStanjuPK != null && !this.naStanjuPK.equals(other.naStanjuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.NaStanju[ naStanjuPK=" + naStanjuPK + " ]";
    }
    
}
