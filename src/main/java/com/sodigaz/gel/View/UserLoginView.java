/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.View;

/**
 *
 * @author Issouf
 */
import com.sodigaz.gel.Entity.Utilisateur;
import com.sodigaz.gel.Service.UtilisateurFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Months;
 
 
@ManagedBean
public class UserLoginView {
    
    @EJB
    private com.sodigaz.gel.Service.UtilisateurFacade utilisateurService;
    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) {
        System.out.println("yes dans le pin");
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            System.out.println("Yes");
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            String rootContext = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String urlSuspn="http://localhost:8080/"+rootContext+"/faces/inspectioncamion/index.xhtml";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(urlSuspn);
            } catch (IOException ex) {
                Logger.getLogger(UserLoginView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("NOn");
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }   
    public String authentificateTheUser() {
        //if(estValide()) permettre a fixer une date d'expiration
        if(true)
        {
            FacesMessage message = null;
        Ini ini = new Ini();
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(ini);
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        System.out.println("ENTRER");               
        Subject currentUser = SecurityUtils.getSubject();        
                
        try {
            
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            currentUser.login(token);  
            final Utilisateur utilisateur = utilisateurService.findUserByUsername(username);
            if (utilisateur == null) {
                System.out.println("No account found for user [" + username + "]");
                return null;
            }else{
                // save current username in the session, so we have access to our User model
                currentUser.getSession().setAttribute("username", username);
                currentUser.getSession().setAttribute("utilisateur", utilisateur);
                System.out.println("USER SAVE SUCCESFULY");
            }
            
            if(username.equals("admin")) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes , Bienvenue ", username);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "/utilisateur/index.xhtml?faces-redirect=true";
            }else {
                return "/acceuil/index.xhtml?faces-redirect=true";
            }
        } 
        catch (UnknownAccountException uae) {
            System.out.println("There is no user with username of ");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identifiant Invalide", username);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } 
        catch (IncorrectCredentialsException ice) {
            System.out.println("Password for account");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identifiant Invalide", username);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } 
        catch (LockedAccountException lae) {
            System.out.println("The account for username");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identifiant Invalide", username);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        catch (AuthenticationException ae) {
            //unexpected condition?  error?
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identifiant Invalide", username);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
            
        }
        
        return null;
        
                          
    } 
    
    public String logout(){
        Ini ini = new Ini();
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(ini);
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject(); 
         try {
            currentUser.logout();
        } 
        catch (Exception e) {
            System.err.println(e);
        }
        
        return "/authentification/index.xhtml?faces-redirect=true";
    }
    
    public void isAnyUserLoggedIn(){
        
        if(SecurityUtils.getSubject().getPrincipal()!=null){
            NavigationHandler nh= FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/authentification/homePage.xhtml?faces-redirect=true");
        }
    }

    public UtilisateurFacade getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(UtilisateurFacade utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    private boolean estValide() {
         
        long b=1545751064000L;        
        if(b<new DateTime().getMillis()){
            System.out.println("Perimer");
            return false;
        }
        else{
            System.out.println("Valide");
            return true;
        }
       
             
    }
    
    
    
    
}