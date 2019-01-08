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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author issouf
 */
@Entity
@Table(name = "fonctionemployer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fonctionemployer.findAll", query = "SELECT f FROM Fonctionemployer f")
    , @NamedQuery(name = "Fonctionemployer.findByIdfonctionemployer", query = "SELECT f FROM Fonctionemployer f WHERE f.idfonctionemployer = :idfonctionemployer")
    , @NamedQuery(name = "Fonctionemployer.findByNomemploi", query = "SELECT f FROM Fonctionemployer f WHERE f.nomemploi = :nomemploi")})
public class Fonctionemployer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfonctionemployer")
    private Integer idfonctionemployer;
    @Size(max = 254)
    @Column(name = "nomemploi")
    private String nomemploi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfonctionemployer")
    private List<Employer> employerList;

    public Fonctionemployer() {
    }

    public Fonctionemployer(Integer idfonctionemployer) {
        this.idfonctionemployer = idfonctionemployer;
    }

    public Integer getIdfonctionemployer() {
        return idfonctionemployer;
    }

    public void setIdfonctionemployer(Integer idfonctionemployer) {
        this.idfonctionemployer = idfonctionemployer;
    }

    public String getNomemploi() {
        return nomemploi;
    }

    public void setNomemploi(String nomemploi) {
        this.nomemploi = nomemploi;
    }

    @XmlTransient
    public List<Employer> getEmployerList() {
        return employerList;
    }

    public void setEmployerList(List<Employer> employerList) {
        this.employerList = employerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfonctionemployer != null ? idfonctionemployer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonctionemployer)) {
            return false;
        }
        Fonctionemployer other = (Fonctionemployer) object;
        if ((this.idfonctionemployer == null && other.idfonctionemployer != null) || (this.idfonctionemployer != null && !this.idfonctionemployer.equals(other.idfonctionemployer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Fonctionemployer[ idfonctionemployer=" + idfonctionemployer + " ]";
    }
    
}
