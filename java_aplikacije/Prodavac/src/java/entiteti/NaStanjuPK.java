/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nemanja
 */
@Embeddable
public class NaStanjuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idProdavnica")
    private int idProdavnica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArtikal")
    private int idArtikal;

    public NaStanjuPK() {
    }

    public NaStanjuPK(int idProdavnica, int idArtikal) {
        this.idProdavnica = idProdavnica;
        this.idArtikal = idArtikal;
    }

    public int getIdProdavnica() {
        return idProdavnica;
    }

    public void setIdProdavnica(int idProdavnica) {
        this.idProdavnica = idProdavnica;
    }

    public int getIdArtikal() {
        return idArtikal;
    }

    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProdavnica;
        hash += (int) idArtikal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NaStanjuPK)) {
            return false;
        }
        NaStanjuPK other = (NaStanjuPK) object;
        if (this.idProdavnica != other.idProdavnica) {
            return false;
        }
        if (this.idArtikal != other.idArtikal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.NaStanjuPK[ idProdavnica=" + idProdavnica + ", idArtikal=" + idArtikal + " ]";
    }
    
}
