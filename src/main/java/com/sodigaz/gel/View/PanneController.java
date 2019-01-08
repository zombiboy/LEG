package com.sodigaz.gel.View;

import com.sodigaz.gel.Entity.Panne;
import com.sodigaz.gel.View.util.JsfUtil;
import com.sodigaz.gel.View.util.JsfUtil.PersistAction;
import com.sodigaz.gel.Service.PanneFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("panneController")
@SessionScoped
public class PanneController implements Serializable {

    @EJB
    private com.sodigaz.gel.Service.PanneFacade ejbFacade;
    private List<Panne> items = null;
    private Panne selected;
    private List<String> typePanne = null;
 
    public PanneController() {
         
    }

    public Panne getSelected() {
        return selected;
    }

    public void setSelected(Panne selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PanneFacade getFacade() {
        return ejbFacade;
    }

    public Panne prepareCreate() {
        selected = new Panne();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PanneCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PanneUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PanneDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Panne> getItems() {
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

    public Panne getPanne(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Panne> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Panne> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
      
    /**
     * 
     * @param i permet de faire une recherche dans la basee
     * @return les panne de type Preciser dans le param type 
     */
    public List<Panne> findPanneBYType(int i) {
        String typeP=getTypesPanne().get(i);
        return getFacade().findPannesByType(typeP);
    }
        
    /**
     * 
     * @return la liste des Pannes avec le TYPE ELECTRICITE qui
     * se retroue a l'indice 0 de la Liste typePanne;
     */
    public List<Panne> getPanneTypeElectricite() {
        String electricite=getTypesPanne().get(0);
        return getFacade().findPannesByType(electricite);
    }
    
    public List<Panne> getPanneTypeVoiture() {
        String electricite=getTypesPanne().get(1);
        return getFacade().findPannesByType(electricite);
    }
    
    public List<Panne> getPanneTypeMoteur() {
        String x=getTypesPanneReparations().get(3);
        return getFacade().findPannesByType(x);
    }
   
    
    public List<Panne> getPanneTypeEssieux() {
        String electricite=getTypesPanne().get(2);
        return getFacade().findPannesByType(electricite);
    }
    
    public List<Panne> getPanneTypeCabine() {
        String electricite=getTypesPanne().get(3);
        return getFacade().findPannesByType(electricite);
    }
    
    public List<Panne> getPanneTypeDivers() {
        String electricite=getTypesPanne().get(4);
        return getFacade().findPannesByType(electricite);
    }
    
    public List<Panne> getPanneTypeTransmission() {
        String x=getTypesPanneReparations().get(4);
        return getFacade().findPannesByType(x);
    }
    
    public List<Panne> getPanneTypeDirection() {
        String x=getTypesPanneReparations().get(5);
        return getFacade().findPannesByType(x);
    }
    
    public List<Panne> getPanneTypePneumatique() {
        String x=getTypesPanneReparations().get(6);
        return getFacade().findPannesByType(x);
    }
    
    public List<Panne> getPanneTypeFreinage() {
        String x=getTypesPanneReparations().get(7);
        return getFacade().findPannesByType(x);
    }
    
    public List<Panne> getPanneTypeSuspension() {
        String x=getTypesPanneReparations().get(8);
        return getFacade().findPannesByType(x);
    }
    
    public List<Panne> getPanneTypeSoudure() {
        String x=getTypesPanneReparations().get(9);
        return getFacade().findPannesByType(x);
    }
    
    
    /**
     * NON UTILISER
     * @return 
     */
    public List<Panne> getPanneTypeDocument() {
        String electricite=getTypesPanne().get(5);
        return getFacade().findPannesByType(electricite);
    }
    
        
    public List<String> getTypesPanne() {
        typePanne= new ArrayList<>();
        typePanne.add("ELECTRICITE"); //0
        typePanne.add("VOITURE");//1
        typePanne.add("ESSIEUX");
        typePanne.add("CABINE");
        typePanne.add("DIVERS");
        typePanne.add("DOCUMENTATION");//5
        return typePanne;
    }
    
    public List<String> getTypesPanneReparations() {
        typePanne= new ArrayList<>();
        typePanne.add("ELECTRICITE"); //0
        typePanne.add("ESSIEUX");//1      
        typePanne.add("DOCUMENTATION");//2
        typePanne.add("MOTEUR");//
        typePanne.add("TRANSMISSION");
        typePanne.add("DIRECTION");//5
        typePanne.add("PNEUMATIQUE");
        typePanne.add("FREINAGE");
        typePanne.add("SUSPENSION");
        typePanne.add("SOUDURE");//9
        return typePanne;
    }
    

    @FacesConverter(forClass = Panne.class)
    public static class PanneControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PanneController controller = (PanneController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "panneController");
            return controller.getPanne(getKey(value));
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
            if (object instanceof Panne) {
                Panne o = (Panne) object;
                return getStringKey(o.getIdpanne());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Panne.class.getName()});
                return null;
            }
        }

    }

}
