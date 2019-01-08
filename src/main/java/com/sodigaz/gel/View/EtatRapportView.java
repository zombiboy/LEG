/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.View;

import com.sodigaz.gel.Entity.Camion;
import com.sodigaz.gel.Service.CamionFacade;
import com.sodigaz.gel.Service.ReparationcamionFacade;
import com.sodigaz.gel.Service.SuspensioncamionFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author issouf
 */
@ManagedBean(name="etatRapportView")
@ViewScoped
public class EtatRapportView {
    
    @EJB
    private com.sodigaz.gel.Service.CamionFacade camionService;
    @EJB
    private com.sodigaz.gel.Service.SuspensioncamionFacade suspensionService;
    @EJB
    private com.sodigaz.gel.Service.ReparationcamionFacade reparationcamionService;
    
    private List<Camion> camions = null;
    private List<Camion> camionsAuGaragge = null;
    
    @PostConstruct
    public void init() {
        camions= camionService.findAll();
        camionsAuGaragge= camionService.findCamionsByEtatSuspension(true);        
        
    }

    public List<Camion> getCamions() {
        return camions;
    }

    public void setCamions(List<Camion> camions) {
        this.camions = camions;
    }

    public CamionFacade getCamionService() {
        return camionService;
    }

    public void setCamionService(CamionFacade camionService) {
        this.camionService = camionService;
    }

    public SuspensioncamionFacade getSuspensionService() {
        return suspensionService;
    }

    public void setSuspensionService(SuspensioncamionFacade suspensionService) {
        this.suspensionService = suspensionService;
    }

    public List<Camion> getCamionsAuGaragge() {
        return camionsAuGaragge;
    }

    public void setCamionsAuGaragge(List<Camion> camionsAuGaragge) {
        this.camionsAuGaragge = camionsAuGaragge;
    }

    public ReparationcamionFacade getReparationcamionService() {
        return reparationcamionService;
    }

    public void setReparationcamionService(ReparationcamionFacade reparationcamionService) {
        this.reparationcamionService = reparationcamionService;
    }
    
    
    
    
}
