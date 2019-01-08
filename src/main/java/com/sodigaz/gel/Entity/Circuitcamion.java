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
@Table(name = "circuitcamion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Circuitcamion.findAll", query = "SELECT c FROM Circuitcamion c")
    , @NamedQuery(name = "Circuitcamion.findByIdcuircuit", query = "SELECT c FROM Circuitcamion c WHERE c.idcuircuit = :idcuircuit")
    , @NamedQuery(name = "Circuitcamion.findByLibellecircuit", query = "SELECT c FROM Circuitcamion c WHERE c.libellecircuit = :libellecircuit")})
public class Circuitcamion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuircuit")
    private Integer idcuircuit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "libellecircuit")
    private String libellecircuit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcuircuit")
    private List<Camion> camionList;

    public Circuitcamion() {
    }

    public Circuitcamion(Integer idcuircuit) {
        this.idcuircuit = idcuircuit;
    }

    public Circuitcamion(Integer idcuircuit, String libellecircuit) {
        this.idcuircuit = idcuircuit;
        this.libellecircuit = libellecircuit;
    }

    public Integer getIdcuircuit() {
        return idcuircuit;
    }

    public void setIdcuircuit(Integer idcuircuit) {
        this.idcuircuit = idcuircuit;
    }

    public String getLibellecircuit() {
        return libellecircuit;
    }

    public void setLibellecircuit(String libellecircuit) {
        this.libellecircuit = libellecircuit;
    }

    @XmlTransient
    public List<Camion> getCamionList() {
        return camionList;
    }

    public void setCamionList(List<Camion> camionList) {
        this.camionList = camionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuircuit != null ? idcuircuit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Circuitcamion)) {
            return false;
        }
        Circuitcamion other = (Circuitcamion) object;
        if ((this.idcuircuit == null && other.idcuircuit != null) || (this.idcuircuit != null && !this.idcuircuit.equals(other.idcuircuit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Circuitcamion[ idcuircuit=" + idcuircuit + " ]";
    }
    
}
