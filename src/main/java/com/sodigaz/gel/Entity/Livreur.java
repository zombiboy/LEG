/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "livreur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livreur.findAll", query = "SELECT l FROM Livreur l")
    , @NamedQuery(name = "Livreur.findByIdlivreur", query = "SELECT l FROM Livreur l WHERE l.idlivreur = :idlivreur")
    , @NamedQuery(name = "Livreur.findByNomlivreur", query = "SELECT l FROM Livreur l WHERE l.nomlivreur = :nomlivreur")
    , @NamedQuery(name = "Livreur.findByPrenomlivreur", query = "SELECT l FROM Livreur l WHERE l.prenomlivreur = :prenomlivreur")
    , @NamedQuery(name = "Livreur.findByTellivreur", query = "SELECT l FROM Livreur l WHERE l.tellivreur = :tellivreur")})
public class Livreur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlivreur")
    private Integer idlivreur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "nomlivreur")
    private String nomlivreur;
    @Size(max = 254)
    @Column(name = "prenomlivreur")
    private String prenomlivreur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "tellivreur")
    private String tellivreur;
    @OneToMany(mappedBy = "idlivreur")
    private List<Camion> camionList;

    public Livreur() {
    }

    public Livreur(Integer idlivreur) {
        this.idlivreur = idlivreur;
    }

    public Livreur(Integer idlivreur, String nomlivreur, String tellivreur) {
        this.idlivreur = idlivreur;
        this.nomlivreur = nomlivreur;
        this.tellivreur = tellivreur;
    }

    public Integer getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(Integer idlivreur) {
        this.idlivreur = idlivreur;
    }

    public String getNomlivreur() {
        return nomlivreur;
    }

    public void setNomlivreur(String nomlivreur) {
        this.nomlivreur = nomlivreur;
    }

    public String getPrenomlivreur() {
        return prenomlivreur;
    }

    public void setPrenomlivreur(String prenomlivreur) {
        this.prenomlivreur = prenomlivreur;
    }

    public String getTellivreur() {
        return tellivreur;
    }

    public void setTellivreur(String tellivreur) {
        this.tellivreur = tellivreur;
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
        hash += (idlivreur != null ? idlivreur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livreur)) {
            return false;
        }
        Livreur other = (Livreur) object;
        if ((this.idlivreur == null && other.idlivreur != null) || (this.idlivreur != null && !this.idlivreur.equals(other.idlivreur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Livreur[ idlivreur=" + idlivreur + " ]";
    }
    
}
