/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.View;

/**
 *
 * @author issouf.ouedraogo
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
 
@ManagedBean
public class UserView {
     
    private String firstname;
    private String lastname;
 
    public String getFirstname() {
        return firstname;
    }
 
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
 
    public String getLastname() {
        return lastname;
    }
 
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
 
    public void save() {        
        PrimeFaces current = PrimeFaces.current();
        if(firstname.equals(lastname)){
            System.out.println("BON +++Lancer le modal de confirmation");
            current.executeScript("PF('dlgBon').show();");
        }else {
            System.out.println("MAUVAIS --- Lancer le modal de confirmation");
            current.executeScript("PF('dlgNon').show();");
        }
    }
    
    public void saveBon() {
        PrimeFaces current = PrimeFaces.current();
        System.out.println("CONFIRME BON  ");
        current.executeScript("PF('dlgBon').hide();");
        FacesContext.getCurrentInstance().addMessage(null,
         new FacesMessage("Welcome " + firstname + " " + lastname));
    }
    
    public void saveMal() {
        System.out.println("CONFIRME MAL");
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + firstname + " " + lastname));
    }
    
}