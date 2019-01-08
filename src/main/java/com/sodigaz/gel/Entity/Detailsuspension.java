/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author issouf
 */
@Entity
@Table(name = "detailsuspension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailsuspension.findAll", query = "SELECT d FROM Detailsuspension d")
    , @NamedQuery(name = "Detailsuspension.findByIddetailsuspension", query = "SELECT d FROM Detailsuspension d WHERE d.iddetailsuspension = :iddetailsuspension")
    , @NamedQuery(name = "Detailsuspension.findByCommentaire", query = "SELECT d FROM Detailsuspension d WHERE d.commentaire = :commentaire")})
public class Detailsuspension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetailsuspension")
    private Integer iddetailsuspension;
    @Size(max = 254)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinColumn(name = "idpanne", referencedColumnName = "idpanne")
    @ManyToOne(optional = false)
    private Panne idpanne;
    @JoinColumn(name = "idsuspensioncamion", referencedColumnName = "idsuspensioncamion")
    @ManyToOne(optional = false)
    private Suspensioncamion idsuspensioncamion;

    public Detailsuspension() {
    }

    public Detailsuspension(Integer iddetailsuspension) {
        this.iddetailsuspension = iddetailsuspension;
    }

    public Integer getIddetailsuspension() {
        return iddetailsuspension;
    }

    public void setIddetailsuspension(Integer iddetailsuspension) {
        this.iddetailsuspension = iddetailsuspension;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Panne getIdpanne() {
        return idpanne;
    }

    public void setIdpanne(Panne idpanne) {
        this.idpanne = idpanne;
    }

    public Suspensioncamion getIdsuspensioncamion() {
        return idsuspensioncamion;
    }

    public void setIdsuspensioncamion(Suspensioncamion idsuspensioncamion) {
        this.idsuspensioncamion = idsuspensioncamion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetailsuspension != null ? iddetailsuspension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailsuspension)) {
            return false;
        }
        Detailsuspension other = (Detailsuspension) object;
        if ((this.iddetailsuspension == null && other.iddetailsuspension != null) || (this.iddetailsuspension != null && !this.iddetailsuspension.equals(other.iddetailsuspension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Detailsuspension[ iddetailsuspension=" + iddetailsuspension + " ]";
    }
    
}
