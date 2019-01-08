package com.sodigaz.gel.View;

import com.sodigaz.gel.Entity.Camion;
import com.sodigaz.gel.Entity.Reparationcamion;
import com.sodigaz.gel.Entity.Suspensioncamion;
import com.sodigaz.gel.Service.CamionFacade;
import com.sodigaz.gel.Service.ReparationcamionFacade;
import com.sodigaz.gel.View.util.JsfUtil;
import com.sodigaz.gel.View.util.JsfUtil.PersistAction;
import com.sodigaz.gel.Service.SuspensioncamionFacade;
import com.sodigaz.gel.View.util.JsfUtil.ETATSCAMION;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name="suspensioncamionController")
@ViewScoped
public class SuspensioncamionController implements Serializable {

    @EJB
    private com.sodigaz.gel.Service.SuspensioncamionFacade ejbFacade;
    private List<Suspensioncamion> items = null;
    private Suspensioncamion selected;
    private Reparationcamion selectedRepar;
    @EJB
    private com.sodigaz.gel.Service.ReparationcamionFacade reparationcamionService;
    
    @EJB
    private com.sodigaz.gel.Service.CamionFacade camionService;

    public SuspensioncamionController() {
    }

    public Suspensioncamion getSelected() {
        return selected;
    }

    public void setSelected(Suspensioncamion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SuspensioncamionFacade getFacade() {
        return ejbFacade;
    }

    public Suspensioncamion prepareCreate() {
        selected = new Suspensioncamion();
        initializeEmbeddableKey();
        return selected;
    }
    
    public Reparationcamion prepareCreateRepar(){
        selectedRepar= new Reparationcamion();
        initializeEmbeddableKey();
        return selectedRepar;
    }

    public Reparationcamion getSelectedRepar() {
        return selectedRepar;
    }

    public void setSelectedRepar(Reparationcamion selectedRepar) {
        this.selectedRepar = selectedRepar;
    }
    
    

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SuspensioncamionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void createRepar() {
        /**
         * le lien est etabli simplement La condition car on peut avoir null
         */
       
        Camion camion = camionService.find(selected.getIdinspectioncamion().getIdcamion().getIdcamion());
        camion.setEtatcamion(ETATSCAMION.EN_REPARATION);
        camionService.edit(camion);
        if(Objects.equals(selected.getEnreparation(), Boolean.TRUE)) {
            System.out.println("deja mi en reparation");
            //notifier avec Dialogue
        }
        else{
            selected.setEnreparation(true);
            ejbFacade.edit(selected);//Pour persister les changements dans la base de donnee
            selectedRepar.setIdsuspensioncamion(selected);
            selectedRepar.setEstreparer(Boolean.FALSE);
            reparationcamionService.edit(selectedRepar);
            selected= new Suspensioncamion();
            selectedRepar= new Reparationcamion();
            //String rootContext = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            //String urlSuspn="http://localhost:8080/"+rootContext+"/faces/reparationcamion/index.xhtml";
            
            NavigationHandler nh= FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/reparationcamion/index.xhtml?faces-redirect=true");
            
        }
        
        //REDIRECTION VERS REPARATION 
        
    }    

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SuspensioncamionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SuspensioncamionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Suspensioncamion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Suspensioncamion getSuspensioncamion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Suspensioncamion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Suspensioncamion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public ReparationcamionFacade getReparationcamionService() {
        return reparationcamionService;
    }

    public void setReparationcamionService(ReparationcamionFacade reparationcamionService) {
        this.reparationcamionService = reparationcamionService;
    }

    public CamionFacade getCamionService() {
        return camionService;
    }

    public void setCamionService(CamionFacade camionService) {
        this.camionService = camionService;
    }

    

    @FacesConverter(forClass = Suspensioncamion.class)
    public static class SuspensioncamionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SuspensioncamionController controller = (SuspensioncamionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "suspensioncamionController");
            return controller.getSuspensioncamion(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Suspensioncamion) {
                Suspensioncamion o = (Suspensioncamion) object;
                return getStringKey(o.getIdsuspensioncamion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Suspensioncamion.class.getName()});
                return null;
            }
        }

    }

}
