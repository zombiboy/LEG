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
import javax.persistence.FetchType;
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
@Table(name = "suspensioncamion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suspensioncamion.findAll", query = "SELECT s FROM Suspensioncamion s")
    , @NamedQuery(name = "Suspensioncamion.findByIdsuspensioncamion", query = "SELECT s FROM Suspensioncamion s WHERE s.idsuspensioncamion = :idsuspensioncamion")
    , @NamedQuery(name = "Suspensioncamion.findByDatesuspensioncamion", query = "SELECT s FROM Suspensioncamion s WHERE s.datesuspensioncamion = :datesuspensioncamion")
    , @NamedQuery(name = "Suspensioncamion.findByHeuresuspensioncamion", query = "SELECT s FROM Suspensioncamion s WHERE s.heuresuspensioncamion = :heuresuspensioncamion")
    , @NamedQuery(name = "Suspensioncamion.findByLieususpensioncamion", query = "SELECT s FROM Suspensioncamion s WHERE s.lieususpensioncamion = :lieususpensioncamion")
    , @NamedQuery(name = "Suspensioncamion.findByEnreparation", query = "SELECT s FROM Suspensioncamion s WHERE s.enreparation = :enreparation")})
public class Suspensioncamion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsuspensioncamion")
    private Integer idsuspensioncamion;
    @Column(name = "datesuspensioncamion")
    @Temporal(TemporalType.DATE)
    private Date datesuspensioncamion;
    @Column(name = "heuresuspensioncamion")
    @Temporal(TemporalType.TIME)
    private Date heuresuspensioncamion;
    @Size(max = 254)
    @Column(name = "lieususpensioncamion")
    private String lieususpensioncamion;
    @Column(name = "enreparation")
    private Boolean enreparation;
    @JoinColumn(name = "idinspectioncamion", referencedColumnName = "idinspectioncamion")
    @ManyToOne
    private Inspectioncamion idinspectioncamion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsuspensioncamion",fetch = FetchType.EAGER)
    private List<Detailsuspension> detailsuspensionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsuspensioncamion")
    private List<Reparationcamion> reparationcamionList;

    public Suspensioncamion() {
    }

    public Suspensioncamion(Integer idsuspensioncamion) {
        this.idsuspensioncamion = idsuspensioncamion;
    }

    public Integer getIdsuspensioncamion() {
        return idsuspensioncamion;
    }

    public void setIdsuspensioncamion(Integer idsuspensioncamion) {
        this.idsuspensioncamion = idsuspensioncamion;
    }

    public Date getDatesuspensioncamion() {
        return datesuspensioncamion;
    }

    public void setDatesuspensioncamion(Date datesuspensioncamion) {
        this.datesuspensioncamion = datesuspensioncamion;
    }

    public Date getHeuresuspensioncamion() {
        return heuresuspensioncamion;
    }

    public void setHeuresuspensioncamion(Date heuresuspensioncamion) {
        this.heuresuspensioncamion = heuresuspensioncamion;
    }

    public String getLieususpensioncamion() {
        return lieususpensioncamion;
    }

    public void setLieususpensioncamion(String lieususpensioncamion) {
        this.lieususpensioncamion = lieususpensioncamion;
    }

    public Boolean getEnreparation() {
        return enreparation;
    }

    public void setEnreparation(Boolean enreparation) {
        this.enreparation = enreparation;
    }

    public Inspectioncamion getIdinspectioncamion() {
        return idinspectioncamion;
    }

    public void setIdinspectioncamion(Inspectioncamion idinspectioncamion) {
        this.idinspectioncamion = idinspectioncamion;
    }

    @XmlTransient
    public List<Detailsuspension> getDetailsuspensionList() {
        return detailsuspensionList;
    }

    public void setDetailsuspensionList(List<Detailsuspension> detailsuspensionList) {
        this.detailsuspensionList = detailsuspensionList;
    }

    @XmlTransient
    public List<Reparationcamion> getReparationcamionList() {
        return reparationcamionList;
    }

    public void setReparationcamionList(List<Reparationcamion> reparationcamionList) {
        this.reparationcamionList = reparationcamionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsuspensioncamion != null ? idsuspensioncamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suspensioncamion)) {
            return false;
        }
        Suspensioncamion other = (Suspensioncamion) object;
        if ((this.idsuspensioncamion == null && other.idsuspensioncamion != null) || (this.idsuspensioncamion != null && !this.idsuspensioncamion.equals(other.idsuspensioncamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Suspensioncamion[ idsuspensioncamion=" + idsuspensioncamion + " ]";
    }
    
}
