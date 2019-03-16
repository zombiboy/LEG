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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;

/**
 *
 * @author issouf
 */
@Entity
@Table(name = "alerteperiodique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alerteperiodique.findAll", query = "SELECT a FROM Alerteperiodique a")
    , @NamedQuery(name = "Alerteperiodique.findByIdalerte", query = "SELECT a FROM Alerteperiodique a WHERE a.idalerte = :idalerte")
    , @NamedQuery(name = "Alerteperiodique.findByLibellealerte", query = "SELECT a FROM Alerteperiodique a WHERE a.libellealerte = :libellealerte")
    , @NamedQuery(name = "Alerteperiodique.findByDatedernieralerte", query = "SELECT a FROM Alerteperiodique a WHERE a.datedernieralerte = :datedernieralerte")
    , @NamedQuery(name = "Alerteperiodique.findByEcheancealerte", query = "SELECT a FROM Alerteperiodique a WHERE a.echeancealerte = :echeancealerte")})
public class Alerteperiodique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalerte")
    private Integer idalerte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "libellealerte")
    private String libellealerte;
    @Column(name = "datedernieralerte")
    @Temporal(TemporalType.DATE)
    private Date datedernieralerte;
    @Column(name = "echeancealerte")
    @Temporal(TemporalType.DATE)
    private Date echeancealerte;
    @JoinColumn(name = "idcamion", referencedColumnName = "idcamion")
    @ManyToOne
    private Camion idcamion;
    
    @Transient
    private int jourrestant;

    public Alerteperiodique() {
    }

    public Alerteperiodique(Integer idalerte) {
        this.idalerte = idalerte;
    }

    public Alerteperiodique(Integer idalerte, String libellealerte) {
        this.idalerte = idalerte;
        this.libellealerte = libellealerte;
    }

    public Integer getIdalerte() {
        return idalerte;
    }

    public void setIdalerte(Integer idalerte) {
        this.idalerte = idalerte;
    }

    public String getLibellealerte() {
        return libellealerte;
    }

    public void setLibellealerte(String libellealerte) {
        this.libellealerte = libellealerte;
    }

    public Date getDatedernieralerte() {
        return datedernieralerte;
    }

    public void setDatedernieralerte(Date datedernieralerte) {
        this.datedernieralerte = datedernieralerte;
    }

    public Date getEcheancealerte() {
        return echeancealerte;
    }

    public void setEcheancealerte(Date echeancealerte) {
        this.echeancealerte = echeancealerte;
    }

    public Camion getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(Camion idcamion) {
        this.idcamion = idcamion;
    }

    public int getJourrestant() {
        if(echeancealerte!=null) {            
            DateTime startDate = new DateTime();
            //DateTime endDate = startDate.plus(Months.months(2));
            DateTime endDate = new DateTime(echeancealerte.getTime());
            Interval interval;
            if(endDate.isAfterNow()){
                interval = new Interval(startDate, endDate);
                int duree=(int) interval.toDuration().getStandardDays();                
                jourrestant=duree;
                return jourrestant;
            }else{
                interval = new Interval(endDate, startDate);
                int duree=(int) interval.toDuration().getStandardDays();                
                jourrestant=(-1)*duree;
                return jourrestant;
            }          
        }
        return 0;
        
        
    }

    public void setJourrestant(int jourrestant) {
        this.jourrestant = jourrestant;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalerte != null ? idalerte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alerteperiodique)) {
            return false;
        }
        Alerteperiodique other = (Alerteperiodique) object;
        if ((this.idalerte == null && other.idalerte != null) || (this.idalerte != null && !this.idalerte.equals(other.idalerte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sodigaz.gel.Entity.Alerteperiodique[ idalerte=" + idalerte + " ]";
    }
    
}
