package com.sodigaz.gel.View;

import com.sodigaz.gel.Entity.Alerteperiodique;
import com.sodigaz.gel.Entity.Camion;
import com.sodigaz.gel.Service.AlerteperiodiqueFacade;
import com.sodigaz.gel.View.util.JsfUtil;
import com.sodigaz.gel.View.util.JsfUtil.PersistAction;
import com.sodigaz.gel.Service.CamionFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@ManagedBean(name="camionController")
@ViewScoped
public class CamionController implements Serializable {

    @EJB
    private com.sodigaz.gel.Service.CamionFacade ejbFacade;
    @EJB
    private com.sodigaz.gel.Service.AlerteperiodiqueFacade alerteperiodiqueService;
    private List<Camion> items = null;
    private Camion selected;
    private List<Camion> filteredCamions;
    private Alerteperiodique selectedAssurance;
    private Alerteperiodique selectedCCVA;

    public CamionController() {
    }

    public Camion getSelected() {
        return selected;
    }

    public void setSelected(Camion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CamionFacade getFacade() {
        return ejbFacade;
    }

    public Camion prepareCreate() {
        selected = new Camion();
        selectedAssurance= new Alerteperiodique();
        selectedCCVA= new Alerteperiodique();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CamionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
    }
    
    public void createCamionAlerte() {
        
        if (selected != null) {
            setEmbeddableKeys();
            try {
                selected=getFacade().add(selected);
                JsfUtil.addSuccessMessage("CamionCreated");   
                
                selectedAssurance.setLibellealerte("ASSURANCE");//A CHANGER
                selectedAssurance.setIdcamion(selected);
                alerteperiodiqueService.edit(selectedAssurance);
                
                selectedCCVA.setLibellealerte("CCVA");//A CHANGER
                selectedCCVA.setIdcamion(selected);
                alerteperiodiqueService.edit(selectedCCVA);
                
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
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CamionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CamionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Camion> getItems() {
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

    public Camion getCamion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Camion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Camion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public List<Camion> getItemsCamionsNonSuspendu() {
        return getFacade().findCamionsByEtatSuspension(false);
    }

    public List<Camion> getFilteredCamions() {
        return filteredCamions;
    }

    public void setFilteredCamions(List<Camion> filteredCamions) {
        this.filteredCamions = filteredCamions;
    }
    
    public List<String> getProprietairesCamion() {
        List<String> proprietairesCamion = new ArrayList<>();
        proprietairesCamion.add("BOLARD");
        proprietairesCamion.add("BOLARO");
        proprietairesCamion.add("CTAG");
        proprietairesCamion.add("SODIGAZ"); 
        return proprietairesCamion;
    }
    
    public List<String> getEtatsCamion() {
        List<String> list = new ArrayList<>();
        list.add("NON UTILISER");
        list.add("EN SERVICE");
        list.add("SUSPENDU");
        list.add("EN REPARATION");
        list.add("REPARER");      
        return list;
    }
    

    public AlerteperiodiqueFacade getAlerteperiodiqueService() {
        return alerteperiodiqueService;
    }

    public void setAlerteperiodiqueService(AlerteperiodiqueFacade alerteperiodiqueService) {
        this.alerteperiodiqueService = alerteperiodiqueService;
    }

    public Alerteperiodique getSelectedAssurance() {
        return selectedAssurance;
    }

    public void setSelectedAssurance(Alerteperiodique selectedAssurance) {
        this.selectedAssurance = selectedAssurance;
    }

    public Alerteperiodique getSelectedCCVA() {
        return selectedCCVA;
    }

    public void setSelectedCCVA(Alerteperiodique selectedCCVA) {
        this.selectedCCVA = selectedCCVA;
    }
    
    

                
    @FacesConverter(forClass = Camion.class)
    public static class CamionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CamionController controller = (CamionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "camionController");
            return controller.getCamion(getKey(value));
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
            if (object instanceof Camion) {
                Camion o = (Camion) object;
                return getStringKey(o.getIdcamion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Camion.class.getName()});
                return null;
            }
        }

    }

}
