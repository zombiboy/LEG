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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "reparationcamion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparationcamion.findAll", query = "SELECT r FROM Reparationcamion r")
    , @NamedQuery(name = "Reparationcamion.findByIdreparationcamion", query = "SELECT r FROM Reparationcamion r WHERE r.idreparationcamion = :idreparationcamion")
    , @NamedQuery(name = "Reparationcamion.findByNomgarage", query = "SELECT r FROM Reparationcamion r WHERE r.nomgarage = :nomgarage")
    , @NamedQuery(name = "Reparationcamion.findByResponsablegarage", query = "SELECT r FROM Reparationcamion r WHERE r.responsablegarage = :responsablegarage")
    , @NamedQuery(name = "Reparationcamion.findByTelgarage", query = "SELECT r FROM Reparationcamion r WHERE r.telgarage = :telgarage")
    , @NamedQuery(name = "Reparationcamion.findByObservationgarage", query = "SELECT r FROM Reparationcamion r WHERE r.observationgarage = :observationgarage")
    , @NamedQuery(name = "Reparationcamion.findByEstreparer", query = "SELECT r FROM Reparationcamion r WHERE r.estreparer = :estreparer")
    , @NamedQuery(name = "Reparationcamion.findByDateentreeprevuegarage", query = "SELECT r FROM Reparationcamion r WHERE r.dateentreeprevuegarage = :dateentreeprevuegarage")
    , @NamedQuery(name = "Reparationcamion.findByDateentreeexactegarage", query = "SELECT r FROM Reparationcamion r WHERE r.dateentreeexactegarage = :dateentreeexactegarage")
    , @NamedQuery(name = "Reparationcamion.findByDatesortieprevuegarage", query = "SELECT r FROM Reparationcamion r WHERE r.datesortieprevuegarage = :datesortieprevuegarage")
    , @NamedQuery(name = "Reparationcamion.findByDatesortieexactegarage", query = "SELECT r FROM Reparationcamion r WHERE r.datesortieexactegarage = :datesortieexactegarage")
    , @NamedQuery(name = "Reparationcamion.findByDateremiseenservice", query = "SELECT r FROM Reparationcamion r WHERE r.dateremiseenservice = :dateremiseenservice")
    , @NamedQuery(name = "Reparationcamion.findByEtatreparation", query = "SELECT r FROM Reparationcamion r WHERE r.etatreparation = :etatreparation")
    , @NamedQuery(name = "Reparationcamion.findByDatereparationcamion", query = "SELECT r FROM Reparationcamion r WHERE r.datereparationcamion = :datereparationcamion")
    , @NamedQuery(name = "Reparationcamion.findByHeurereparationcamion", query = "SELECT r FROM Reparationcamion r WHERE r.heurereparationcamion = :heurereparationcamion")})
public class Reparationcamion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreparationcamion")
    private Integer idreparationcamion;
    @Size(max = 254)
    @Column(name = "nomgarage")
    private String nomgarage;
    @Size(max = 254)
    @Column(name = "responsablegarage")
    private String responsablegarage;
    @Size(max = 254)
    @Column(name = "telgarage")
    private String telgarage;
    @Size(max = 254)
    @Column(name = "observationgarage")
    private String observationgarage;
    @Column(name = "estreparer")
    private Boolean estreparer;
    @Column(name = "dateentreeprevuegarage")
    @Temporal(TemporalType.DATE)
    private Date dateentreeprevuegarage;
    @Column(name = "dateentreeexactegarage")
    @Temporal(TemporalType.DATE)
    private Date dateentreeexactegarage;
    @Column(name = "datesortieprevuegarage")
    @Temporal(TemporalType.DATE)
    private Date datesortieprevuegarage;
    @Column(name = "datesortieexactegarage")
    @Temporal(TemporalType.DATE)
    private Date datesortieexactegarage;
    @Column(name = "dateremiseenservice")
    @Temporal(TemporalType.DATE)
    private Date dateremiseenservice;
    @Size(max = 254)
    @Column(name = "etatreparation")
    private String etatreparation;
    @Column(name = "datereparationcamion")
    @Temporal(TemporalType.DATE)
    private Date datereparationcamion;
    @Column(name = "heurereparationcamion")
    @Temporal(TemporalType.TIME)
    private Date heurereparationcamion;
    @ManyToMany(mappedBy = "reparationcamionList")
    private List<Panne> panneList;
    @JoinColumn(name = "idsuspensioncamion", referencedColumnName = "idsuspensioncamion")
    @ManyToOne(optional = false)
    private Suspensioncamion idsuspensioncamion;

    public Reparationcamion() {
    }

    public Reparationcamion(Integer idreparationcamion) {
        this.idreparationcamion = idreparationcamion;
    }

    public Integer getIdreparationcamion() {
        return idreparationcamion;
    }

    public void setIdreparationcamion(Integer idreparationcamion) {
        this.idreparationcamion = idreparationcamion;
    }

    public String getNomgarage() {
        return nomgarage;
    }

    public void setNomgarage(String nomgarage) {
        this.nomgarage = nomgarage;
    }

    public String getResponsablegarage() {
        return responsablegarage;
    }

    public void setResponsablegarage(String responsablegarage) {
        this.responsablegarage = responsablegarage;
    }

    public String getTelgarage() {
        return telgarage;
    }

    public void setTelgarage(String telgarage) {
        this.telgarage = telgarage;
    }

    public String getObservationgarage() {
        return observationgarage;
    }

    public void setObservationgarage(String observationgarage) {
        this.observationgarage = observationgarage;
    }

    public Boolean getEstreparer() {
        return estreparer;
    }

    public void setEstreparer(Boolean estreparer) {
        this.estreparer = estreparer;
    }

    public Date getDateentreeprevuegarage() {
        return dateentreeprevuegarage;
    }

    public void setDateentreeprevuegarage(Date dateentreeprevuegarage) {
        this.dateentreeprevuegarage = dateentreeprevuegarage;
    }

    public Date getDateentreeexactegarage() {
        return dateentreeexactegarage;
    }

    public void setDateentreeexactegarage(Date dateentreeexactegarage) {
        this.dateentreeexactegarage = dateentreeexactegarage;
    }

    public Date getDatesortieprevuegarage() {
        return datesortieprevuegarage;
    }

    public void setDatesortieprevuegarage(Date datesortieprevuegarage) {
        this.datesortieprevuegarage = datesortieprevuegarage;
    }

    public Date getDatesortieexactegarage() {
        return datesortieexactegarage;
    }

    public void setDatesortieexactegarage(Date datesortieexactegarage) {
        this.datesortieexactegarage = datesortieexactegarage;
    }

    public Date getDateremiseenservice() {
        return dateremiseenservice;
    }

    public void setDateremiseenservice(Date dateremiseenservice) {
        this.dateremiseenservice = dateremiseenservice;
    }

    public String getEtatreparation() {
        return etatreparation;
    }

    public void setEtatreparation(String etatreparation) {
        this.etatreparation = etatreparation;
    }

    public Date getDatereparationcamion() {
        return datereparationcamion;
    }

    public void setDatereparationcamion(Date datereparationcamion) {
        this.datereparationcamion = datereparationcamion;
    }

    public Date getHeurereparationcamion() {
        return heurereparationcamion;
    }

    public void setHeurereparationcamion(Date heurereparationcamion) {
        this.heurereparationcamion = heurereparationcamion;
    }

    @XmlTransient
    public List<Panne> getPanneList() {
        return panneList;
    }

    public void setPanneList(List<Panne> panneList) {
        this.panneList = panneList;
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
        hash += (idreparationcamion != null ? idreparationcamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparationcamion)) {
            return false;
        }
        Reparationcamion other = (Reparationcamion) object;
        if ((this.idreparationcamion == null && other.idreparationcamion != null) || (this.idreparationcamion != null && !this.idreparationcamion.equals(other.idreparationcamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Reparationcamion[ idreparationcamion=" + idreparationcamion + " ]";
    }
    
}
