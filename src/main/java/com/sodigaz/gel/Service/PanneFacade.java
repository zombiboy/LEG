/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Service;

import com.sodigaz.gel.Entity.Panne;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author issouf
 */
@Stateless
public class PanneFacade extends AbstractFacade<Panne> {

    @PersistenceContext(unitName = "com.mycompany_sodigazGEL2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PanneFacade() {
        super(Panne.class);
    }
    
    public List<Panne> findAllByType(String typePanne){
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Panne.class));
        return em.createQuery(cq).getResultList();
    }
    
    public List<Panne> findPannesByType(String a){        
        return em.createNamedQuery("Panne.findByTypepanne").setParameter("typepanne",a).getResultList();
    }
    
}
