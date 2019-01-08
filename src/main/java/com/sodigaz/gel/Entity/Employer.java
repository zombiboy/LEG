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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author issouf
 */
@Entity
@Table(name = "employer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employer.findAll", query = "SELECT e FROM Employer e")
    , @NamedQuery(name = "Employer.findByIdemployer", query = "SELECT e FROM Employer e WHERE e.idemployer = :idemployer")
    , @NamedQuery(name = "Employer.findByNom", query = "SELECT e FROM Employer e WHERE e.nom = :nom")
    , @NamedQuery(name = "Employer.findByPrenom", query = "SELECT e FROM Employer e WHERE e.prenom = :prenom")
    , @NamedQuery(name = "Employer.findByTelephone", query = "SELECT e FROM Employer e WHERE e.telephone = :telephone")
    , @NamedQuery(name = "Employer.findByMatricule", query = "SELECT e FROM Employer e WHERE e.matricule = :matricule")})
public class Employer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemployer")
    private Integer idemployer;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 254)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 254)
    @Column(name = "matricule")
    private String matricule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idemployer")
    private List<Utilisateur> utilisateurList;
    @JoinColumn(name = "idfonctionemployer", referencedColumnName = "idfonctionemployer")
    @ManyToOne(optional = false)
    private Fonctionemployer idfonctionemployer;

    public Employer() {
    }

    public Employer(Integer idemployer) {
        this.idemployer = idemployer;
    }

    public Integer getIdemployer() {
        return idemployer;
    }

    public void setIdemployer(Integer idemployer) {
        this.idemployer = idemployer;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    public Fonctionemployer getIdfonctionemployer() {
        return idfonctionemployer;
    }

    public void setIdfonctionemployer(Fonctionemployer idfonctionemployer) {
        this.idfonctionemployer = idfonctionemployer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemployer != null ? idemployer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employer)) {
            return false;
        }
        Employer other = (Employer) object;
        if ((this.idemployer == null && other.idemployer != null) || (this.idemployer != null && !this.idemployer.equals(other.idemployer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Employer[ idemployer=" + idemployer + " ]";
    }
    
}
