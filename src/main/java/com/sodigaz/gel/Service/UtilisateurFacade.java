/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.Service;

import com.sodigaz.gel.Entity.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;

/**
 *
 * @author issouf
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "com.mycompany_sodigazGEL2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    public Utilisateur findUserByUsername(String a){        
        return (Utilisateur) em.createNamedQuery("Utilisateur.findByUsername").setParameter("username",a).getSingleResult();
    }
    
    public Utilisateur getCurrentUser() {
        
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
          //String username = (String) currentUser.getSession().getAttribute("username");
          Utilisateur user = (Utilisateur) currentUser.getSession().getAttribute("utilisateur");
          return user;
        } else {
          return null;
        }
    }
    
}
