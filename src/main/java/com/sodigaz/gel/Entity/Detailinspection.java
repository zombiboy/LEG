/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author issouf
 */
@Entity
@Table(name = "detailinspection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailinspection.findAll", query = "SELECT d FROM Detailinspection d")
    , @NamedQuery(name = "Detailinspection.findByIddetailinspection", query = "SELECT d FROM Detailinspection d WHERE d.iddetailinspection = :iddetailinspection")
    , @NamedQuery(name = "Detailinspection.findByDatedelaireparation", query = "SELECT d FROM Detailinspection d WHERE d.datedelaireparation = :datedelaireparation")
    , @NamedQuery(name = "Detailinspection.findByCommentaire", query = "SELECT d FROM Detailinspection d WHERE d.commentaire = :commentaire")
    , @NamedQuery(name = "Detailinspection.findByIdInspectionCamion", query = "SELECT d FROM Detailinspection d WHERE d.idinspectioncamion.idinspectioncamion = :idinspectioncamion")        
    , @NamedQuery(name = "Detailinspection.findByEtatalerte", query = "SELECT d FROM Detailinspection d WHERE d.etatalerte = :etatalerte")})
public class Detailinspection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetailinspection")
    private Integer iddetailinspection;
    @Column(name = "datedelaireparation")
    @Temporal(TemporalType.DATE)
    private Date datedelaireparation;
    @Size(max = 254)
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "etatalerte")
    private Boolean etatalerte;
    @JoinColumn(name = "idinspectioncamion", referencedColumnName = "idinspectioncamion")
    @ManyToOne(optional = false)
    private Inspectioncamion idinspectioncamion;
    @JoinColumn(name = "idpanne", referencedColumnName = "idpanne")
    @ManyToOne(optional = false)
    private Panne idpanne;

    public Detailinspection() {
    }

    public Detailinspection(Integer iddetailinspection) {
        this.iddetailinspection = iddetailinspection;
    }

    public Integer getIddetailinspection() {
        return iddetailinspection;
    }

    public void setIddetailinspection(Integer iddetailinspection) {
        this.iddetailinspection = iddetailinspection;
    }

    public Date getDatedelaireparation() {
        return datedelaireparation;
    }

    public void setDatedelaireparation(Date datedelaireparation) {
        this.datedelaireparation = datedelaireparation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Boolean getEtatalerte() {
        return etatalerte;
    }

    public void setEtatalerte(Boolean etatalerte) {
        this.etatalerte = etatalerte;
    }

    public Inspectioncamion getIdinspectioncamion() {
        return idinspectioncamion;
    }

    public void setIdinspectioncamion(Inspectioncamion idinspectioncamion) {
        this.idinspectioncamion = idinspectioncamion;
    }

    public Panne getIdpanne() {
        return idpanne;
    }

    public void setIdpanne(Panne idpanne) {
        this.idpanne = idpanne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetailinspection != null ? iddetailinspection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailinspection)) {
            return false;
        }
        Detailinspection other = (Detailinspection) object;
        if ((this.iddetailinspection == null && other.iddetailinspection != null) || (this.iddetailinspection != null && !this.iddetailinspection.equals(other.iddetailinspection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Detailinspection[ iddetailinspection=" + iddetailinspection + " ]";
    }
    
}
