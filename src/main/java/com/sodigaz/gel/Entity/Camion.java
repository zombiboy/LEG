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
import javax.persistence.ManyToOne;
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
@Table(name = "camion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c")
    , @NamedQuery(name = "Camion.findByIdcamion", query = "SELECT c FROM Camion c WHERE c.idcamion = :idcamion")
    , @NamedQuery(name = "Camion.findByImmatriculation", query = "SELECT c FROM Camion c WHERE c.immatriculation = :immatriculation")
    , @NamedQuery(name = "Camion.findByProprietairecamion", query = "SELECT c FROM Camion c WHERE c.proprietairecamion = :proprietairecamion")
    , @NamedQuery(name = "Camion.findByCompteurcamion", query = "SELECT c FROM Camion c WHERE c.compteurcamion = :compteurcamion")
    , @NamedQuery(name = "Camion.findByEstsuspension", query = "SELECT c FROM Camion c WHERE c.estsuspension = :estsuspension")
    , @NamedQuery(name = "Camion.findByEtatcamion", query = "SELECT c FROM Camion c WHERE c.etatcamion = :etatcamion")
    , @NamedQuery(name = "Camion.findByCodecamion", query = "SELECT c FROM Camion c WHERE c.codecamion = :codecamion")})
public class Camion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcamion")
    private Integer idcamion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "immatriculation")
    private String immatriculation;
    @Size(max = 254)
    @Column(name = "proprietairecamion")
    private String proprietairecamion;
    @Column(name = "compteurcamion")
    private Integer compteurcamion;
    @Column(name = "estsuspension")
    private Boolean estsuspension;
    @Size(max = 254)
    @Column(name = "etatcamion")
    private String etatcamion;
    @Size(max = 254)
    @Column(name = "codecamion")
    private String codecamion;
    @JoinColumn(name = "idcuircuit", referencedColumnName = "idcuircuit")
    @ManyToOne(optional = false)
    private Circuitcamion idcuircuit;
    @JoinColumn(name = "idlivreur", referencedColumnName = "idlivreur")
    @ManyToOne
    private Livreur idlivreur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcamion")
    private List<Inspectioncamion> inspectioncamionList;
    @OneToMany(mappedBy = "idcamion")
    private List<Alerteperiodique> alerteperiodiqueList;

    public Camion() {
    }

    public Camion(Integer idcamion) {
        this.idcamion = idcamion;
    }

    public Camion(Integer idcamion, String immatriculation) {
        this.idcamion = idcamion;
        this.immatriculation = immatriculation;
    }

    public Integer getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Integer idcamion) {
        this.idcamion = idcamion;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getProprietairecamion() {
        return proprietairecamion;
    }

    public void setProprietairecamion(String proprietairecamion) {
        this.proprietairecamion = proprietairecamion;
    }

    public Integer getCompteurcamion() {
        return compteurcamion;
    }

    public void setCompteurcamion(Integer compteurcamion) {
        this.compteurcamion = compteurcamion;
    }

    public Boolean getEstsuspension() {
        return estsuspension;
    }

    public void setEstsuspension(Boolean estsuspension) {
        this.estsuspension = estsuspension;
    }

    public String getEtatcamion() {
        return etatcamion;
    }

    public void setEtatcamion(String etatcamion) {
        this.etatcamion = etatcamion;
    }

    public String getCodecamion() {
        return codecamion;
    }

    public void setCodecamion(String codecamion) {
        this.codecamion = codecamion;
    }

    public Circuitcamion getIdcuircuit() {
        return idcuircuit;
    }

    public void setIdcuircuit(Circuitcamion idcuircuit) {
        this.idcuircuit = idcuircuit;
    }

    public Livreur getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(Livreur idlivreur) {
        this.idlivreur = idlivreur;
    }

    @XmlTransient
    public List<Inspectioncamion> getInspectioncamionList() {
        return inspectioncamionList;
    }

    public void setInspectioncamionList(List<Inspectioncamion> inspectioncamionList) {
        this.inspectioncamionList = inspectioncamionList;
    }

    @XmlTransient
    public List<Alerteperiodique> getAlerteperiodiqueList() {
        return alerteperiodiqueList;
    }

    public void setAlerteperiodiqueList(List<Alerteperiodique> alerteperiodiqueList) {
        this.alerteperiodiqueList = alerteperiodiqueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcamion != null ? idcamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.idcamion == null && other.idcamion != null) || (this.idcamion != null && !this.idcamion.equals(other.idcamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Camion[ idcamion=" + idcamion + " ]";
    }
    
}
