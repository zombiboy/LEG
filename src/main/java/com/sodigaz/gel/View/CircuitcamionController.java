package com.sodigaz.gel.View;

import com.sodigaz.gel.Entity.Circuitcamion;
import com.sodigaz.gel.View.util.JsfUtil;
import com.sodigaz.gel.View.util.JsfUtil.PersistAction;
import com.sodigaz.gel.Service.CircuitcamionFacade;

import java.io.Serializable;
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

@Named("circuitcamionController")
@SessionScoped
public class CircuitcamionController implements Serializable {

    @EJB
    private com.sodigaz.gel.Service.CircuitcamionFacade ejbFacade;
    private List<Circuitcamion> items = null;
    private Circuitcamion selected;

    public CircuitcamionController() {
    }

    public Circuitcamion getSelected() {
        return selected;
    }

    public void setSelected(Circuitcamion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CircuitcamionFacade getFacade() {
        return ejbFacade;
    }

    public Circuitcamion prepareCreate() {
        selected = new Circuitcamion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CircuitcamionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CircuitcamionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CircuitcamionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Circuitcamion> getItems() {
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

    public Circuitcamion getCircuitcamion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Circuitcamion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Circuitcamion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Circuitcamion.class)
    public static class CircuitcamionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CircuitcamionController controller = (CircuitcamionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "circuitcamionController");
            return controller.getCircuitcamion(getKey(value));
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
            if (object instanceof Circuitcamion) {
                Circuitcamion o = (Circuitcamion) object;
                return getStringKey(o.getIdcuircuit());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Circuitcamion.class.getName()});
                return null;
            }
        }

    }

}
