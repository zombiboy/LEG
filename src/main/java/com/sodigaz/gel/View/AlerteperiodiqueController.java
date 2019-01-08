package com.sodigaz.gel.View;

import com.sodigaz.gel.Entity.Alerteperiodique;
import com.sodigaz.gel.View.util.JsfUtil;
import com.sodigaz.gel.View.util.JsfUtil.PersistAction;
import com.sodigaz.gel.Service.AlerteperiodiqueFacade;

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

@ManagedBean(name="alerteperiodiqueController")
@ViewScoped
public class AlerteperiodiqueController implements Serializable {

    @EJB
    private com.sodigaz.gel.Service.AlerteperiodiqueFacade ejbFacade;
    private List<Alerteperiodique> items = null;
    private Alerteperiodique selected;
    private List<Alerteperiodique> filteredAlerte;
    
    public static List<String> ls= new ArrayList() {{
        add("ASSURANCE");
        add("Rio");
        add("Tokyo");
    }};

    public AlerteperiodiqueController() {
    }

    public Alerteperiodique getSelected() {
        return selected;
    }

    public void setSelected(Alerteperiodique selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AlerteperiodiqueFacade getFacade() {
        return ejbFacade;
    }

    public Alerteperiodique prepareCreate() {
        selected = new Alerteperiodique();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AlerteperiodiqueCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AlerteperiodiqueUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AlerteperiodiqueDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Alerteperiodique> getItems() {
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

    public Alerteperiodique getAlerteperiodique(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Alerteperiodique> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Alerteperiodique> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Alerteperiodique> getFilteredAlerte() {
        return filteredAlerte;
    }

    public void setFilteredAlerte(List<Alerteperiodique> filteredAlerte) {
        this.filteredAlerte = filteredAlerte;
    }
    
    
    
    public List<String> typesAlertePeriodique() {
        List<String> list= new ArrayList<>();
        list.add("ASSURANCE");
        list.add("CCVA");
        return list;
    }

    @FacesConverter(forClass = Alerteperiodique.class)
    public static class AlerteperiodiqueControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlerteperiodiqueController controller = (AlerteperiodiqueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alerteperiodiqueController");
            return controller.getAlerteperiodique(getKey(value));
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
            if (object instanceof Alerteperiodique) {
                Alerteperiodique o = (Alerteperiodique) object;
                return getStringKey(o.getIdalerte());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Alerteperiodique.class.getName()});
                return null;
            }
        }

    }

}
