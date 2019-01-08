/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author issouf
 */
@Entity
@Table(name = "panne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Panne.findAll", query = "SELECT p FROM Panne p")
    , @NamedQuery(name = "Panne.findByIdpanne", query = "SELECT p FROM Panne p WHERE p.idpanne = :idpanne")
    , @NamedQuery(name = "Panne.findByDesignationpanne", query = "SELECT p FROM Panne p WHERE p.designationpanne = :designationpanne")
    , @NamedQuery(name = "Panne.findByDosuspend", query = "SELECT p FROM Panne p WHERE p.dosuspend = :dosuspend")
    , @NamedQuery(name = "Panne.findByTypepanne", query = "SELECT p FROM Panne p WHERE p.typepanne = :typepanne")
    , @NamedQuery(name = "Panne.findByDureedatebutoirjrs", query = "SELECT p FROM Panne p WHERE p.dureedatebutoirjrs = :dureedatebutoirjrs")})
public class Panne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpanne")
    private Integer idpanne;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "designationpanne")
    private String designationpanne;
    @Column(name = "dosuspend")
    private Boolean dosuspend;
    @Size(max = 254)
    @Column(name = "typepanne")
    private String typepanne;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dureedatebutoirjrs")
    private int dureedatebutoirjrs;
    @JoinTable(name = "association_12", joinColumns = {
        @JoinColumn(name = "idpanne", referencedColumnName = "idpanne")}, inverseJoinColumns = {
        @JoinColumn(name = "idreparationcamion", referencedColumnName = "idreparationcamion")})
    @ManyToMany
    private List<Reparationcamion> reparationcamionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpanne")
    private List<Detailsuspension> detailsuspensionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpanne")
    private List<Detailinspection> detailinspectionList;

    public Panne() {
    }

    public Panne(Integer idpanne) {
        this.idpanne = idpanne;
    }

    public Panne(Integer idpanne, String designationpanne, int dureedatebutoirjrs) {
        this.idpanne = idpanne;
        this.designationpanne = designationpanne;
        this.dureedatebutoirjrs = dureedatebutoirjrs;
    }

    public Integer getIdpanne() {
        return idpanne;
    }

    public void setIdpanne(Integer idpanne) {
        this.idpanne = idpanne;
    }

    public String getDesignationpanne() {
        return designationpanne;
    }

    public void setDesignationpanne(String designationpanne) {
        this.designationpanne = designationpanne;
    }

    public Boolean getDosuspend() {
        return dosuspend;
    }

    public void setDosuspend(Boolean dosuspend) {
        this.dosuspend = dosuspend;
    }

    public String getTypepanne() {
        return typepanne;
    }

    public void setTypepanne(String typepanne) {
        this.typepanne = typepanne;
    }

    public int getDureedatebutoirjrs() {
        return dureedatebutoirjrs;
    }

    public void setDureedatebutoirjrs(int dureedatebutoirjrs) {
        this.dureedatebutoirjrs = dureedatebutoirjrs;
    }

    @XmlTransient
    public List<Reparationcamion> getReparationcamionList() {
        return reparationcamionList;
    }

    public void setReparationcamionList(List<Reparationcamion> reparationcamionList) {
        this.reparationcamionList = reparationcamionList;
    }

    @XmlTransient
    public List<Detailsuspension> getDetailsuspensionList() {
        return detailsuspensionList;
    }

    public void setDetailsuspensionList(List<Detailsuspension> detailsuspensionList) {
        this.detailsuspensionList = detailsuspensionList;
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
        hash += (idpanne != null ? idpanne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Panne)) {
            return false;
        }
        Panne other = (Panne) object;
        if ((this.idpanne == null && other.idpanne != null) || (this.idpanne != null && !this.idpanne.equals(other.idpanne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Panne[ idpanne=" + idpanne + " ]";
    }
    
}
