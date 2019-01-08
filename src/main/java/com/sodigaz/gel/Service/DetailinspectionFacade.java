/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Service;

import com.sodigaz.gel.Entity.Detailinspection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author issouf
 */
@Stateless
public class DetailinspectionFacade extends AbstractFacade<Detailinspection> {

    @PersistenceContext(unitName = "com.mycompany_sodigazGEL2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailinspectionFacade() {
        super(Detailinspection.class);
    }
    
    public List<Detailinspection> findByIdInspectionCamion(int id){        
        return em.createNamedQuery("Detailinspection.findByIdInspectionCamion").setParameter("idinspectioncamion",id).getResultList();
    }
    
}
