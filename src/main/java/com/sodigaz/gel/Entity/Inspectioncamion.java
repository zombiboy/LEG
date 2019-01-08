/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author issouf
 */
@Entity
@Table(name = "inspectioncamion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inspectioncamion.findAll", query = "SELECT i FROM Inspectioncamion i")
    , @NamedQuery(name = "Inspectioncamion.findByIdinspectioncamion", query = "SELECT i FROM Inspectioncamion i WHERE i.idinspectioncamion = :idinspectioncamion")
    , @NamedQuery(name = "Inspectioncamion.findByDatecontrolecamion", query = "SELECT i FROM Inspectioncamion i WHERE i.datecontrolecamion = :datecontrolecamion")
    , @NamedQuery(name = "Inspectioncamion.findByHeurecontrolecamion", query = "SELECT i FROM Inspectioncamion i WHERE i.heurecontrolecamion = :heurecontrolecamion")
    , @NamedQuery(name = "Inspectioncamion.findByCompteurcamion", query = "SELECT i FROM Inspectioncamion i WHERE i.compteurcamion = :compteurcamion")
    , @NamedQuery(name = "Inspectioncamion.findByCommentaire", query = "SELECT i FROM Inspectioncamion i WHERE i.commentaire = :commentaire")})
public class Inspectioncamion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinspectioncamion")
    private Integer idinspectioncamion;
    @Column(name = "datecontrolecamion")
    @Temporal(TemporalType.DATE)
    private Date datecontrolecamion;
    @Column(name = "heurecontrolecamion")
    @Temporal(TemporalType.TIME)
    private Date heurecontrolecamion;
    @Column(name = "compteurcamion")
    private Integer compteurcamion;
    @Size(max = 254)
    @Column(name = "commentaire")
    private String commentaire;
    @OneToMany(mappedBy = "idinspectioncamion")
    private List<Suspensioncamion> suspensioncamionList;
    @JoinColumn(name = "idcamion", referencedColumnName = "idcamion")
    @ManyToOne(optional = false)
    private Camion idcamion;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idutilisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idinspectioncamion")
    private List<Detailinspection> detailinspectionList;

    public Inspectioncamion() {
    }

    public Inspectioncamion(Integer idinspectioncamion) {
        this.idinspectioncamion = idinspectioncamion;
    }

    public Integer getIdinspectioncamion() {
        return idinspectioncamion;
    }

    public void setIdinspectioncamion(Integer idinspectioncamion) {
        this.idinspectioncamion = idinspectioncamion;
    }

    public Date getDatecontrolecamion() {
        return datecontrolecamion;
    }

    public void setDatecontrolecamion(Date datecontrolecamion) {
        this.datecontrolecamion = datecontrolecamion;
    }

    public Date getHeurecontrolecamion() {
        return heurecontrolecamion;
    }

    public void setHeurecontrolecamion(Date heurecontrolecamion) {
        this.heurecontrolecamion = heurecontrolecamion;
    }

    public Integer getCompteurcamion() {
        return compteurcamion;
    }

    public void setCompteurcamion(Integer compteurcamion) {
        this.compteurcamion = compteurcamion;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @XmlTransient
    public List<Suspensioncamion> getSuspensioncamionList() {
        return suspensioncamionList;
    }

    public void setSuspensioncamionList(List<Suspensioncamion> suspensioncamionList) {
        this.suspensioncamionList = suspensioncamionList;
    }

    public Camion getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Camion idcamion) {
        this.idcamion = idcamion;
    }

    public Utilisateur getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Utilisateur idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    @XmlTransient
    public List<Detailinspection> getDetailinspectionList() {
        return detailinspectionList;
    }

    public void setDetailinspectionList(List<Detailinspection> detailinspectionList) {
        this.detailinspectionList = detailinspectionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinspectioncamion != null ? idinspectioncamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inspectioncamion)) {
            return false;
        }
        Inspectioncamion other = (Inspectioncamion) object;
        if ((this.idinspectioncamion == null && other.idinspectioncamion != null) || (this.idinspectioncamion != null && !this.idinspectioncamion.equals(other.idinspectioncamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Inspectioncamion[ idinspectioncamion=" + idinspectioncamion + " ]";
    }
    
}
