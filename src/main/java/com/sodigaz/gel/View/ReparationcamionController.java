package com.sodigaz.gel.View;

import com.sodigaz.gel.Entity.Camion;
import com.sodigaz.gel.Entity.Detailinspection;
import com.sodigaz.gel.Entity.Detailsuspension;
import com.sodigaz.gel.Entity.Reparationcamion;
import com.sodigaz.gel.Service.CamionFacade;
import com.sodigaz.gel.Service.DetailinspectionFacade;
import com.sodigaz.gel.Service.DetailsuspensionFacade;
import com.sodigaz.gel.View.util.JsfUtil;
import com.sodigaz.gel.View.util.JsfUtil.PersistAction;
import com.sodigaz.gel.Service.ReparationcamionFacade;
import com.sodigaz.gel.View.util.JsfUtil.ETATSCAMION;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@ManagedBean(name="reparationcamionController")
@ViewScoped
public class ReparationcamionController implements Serializable {

    @EJB
    private com.sodigaz.gel.Service.ReparationcamionFacade ejbFacade;
    @EJB
    private com.sodigaz.gel.Service.CamionFacade camionService;
    @EJB
    private com.sodigaz.gel.Service.DetailsuspensionFacade detailsuspensionService;
    @EJB
    private com.sodigaz.gel.Service.DetailinspectionFacade detailinspectionService;
    private List<Reparationcamion> items = null;
    private List<Reparationcamion> filteredReparations;
    private Reparationcamion selected;
    private JasperPrint jasperPrint;

    public ReparationcamionController() {
    }

    public Reparationcamion getSelected() {
        return selected;
    }

    public void setSelected(Reparationcamion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReparationcamionFacade getFacade() {
        return ejbFacade;
    }

    public Reparationcamion prepareCreate() {
        selected = new Reparationcamion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReparationcamionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void validerReparation(){
        System.out.println("TAILLE DETAIL"+selected.getIdsuspensioncamion().getDetailsuspensionList().size());
        selected.setEstreparer(Boolean.TRUE);
        selected.setDateremiseenservice(new Date());
        selected.setEtatreparation(ETATSCAMION.REPARER);
        selected.setHeurereparationcamion(new Time(new Date().getTime()));
        selected.setDatereparationcamion(new Date());
        
        Camion camion= camionService.find(selected.getIdsuspensioncamion().getIdinspectioncamion().getIdcamion().getIdcamion());
        camion.setEtatcamion(ETATSCAMION.EN_SERVICE);
        camion.setEstsuspension(false); // Utiliser pour avoir le camion non suspendu dans ChekList
        camionService.edit(camion);
        ejbFacade.edit(selected);
        persist(PersistAction.UPDATE, "Voiture Reparer Avec Succes");
        
        mettreAJourETATDetailInspection();
        
    }
    
    public void validerMettreEnService(){
        
        //selected.setEnservice(Boolean.TRUE);        
        //Mettre le status de Camion en Service
        //camionService.find(selected.getIdsuspensioncamion().getIdinspectioncamion().getIdcamion().getIdcamion());
        Camion camion = camionService.find(selected.getIdsuspensioncamion().getIdinspectioncamion().getIdcamion().getIdcamion());
        camion.setEtatcamion(ETATSCAMION.EN_SERVICE);
        camionService.edit(camion);
        System.out.println("camion trouver =ETAT"+camion.getEtatcamion()+"id=="+camion.getIdcamion());
        persist(PersistAction.UPDATE, "Voiture Mise En Service Avec Succes");
    }
        

    public void update() {
        List<Detailsuspension> l=getDetailSuspensionById();
        for (Detailsuspension detailsuspension : l) {
            System.out.println("alon"+detailsuspension.getIddetailsuspension());
        }
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReparationcamionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReparationcamionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reparationcamion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Reparationcamion> getItemsEnReparation() {
        
        return getFacade().findCamionsEnReparation(false);
    }
    

    public List<Detailsuspension> getDetailSuspensionById() {
        
        List<Detailsuspension> listDetailSus=detailsuspensionService.findAll();
        return listDetailSus;
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

    public Reparationcamion getReparationcamion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Reparationcamion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reparationcamion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Reparationcamion> getFilteredReparations() {
        return filteredReparations;
    }

    public void setFilteredReparations(List<Reparationcamion> filteredReparations) {
        this.filteredReparations = filteredReparations;
    }

    
    
    public CamionFacade getCamionService() {
        return camionService;
    }

    public void setCamionService(CamionFacade camionService) {
        this.camionService = camionService;
    }

    public DetailsuspensionFacade getDetailsuspensionService() {
        return detailsuspensionService;
    }

    public void setDetailsuspensionService(DetailsuspensionFacade detailsuspensionService) {
        this.detailsuspensionService = detailsuspensionService;
    }

    public DetailinspectionFacade getDetailinspectionService() {
        return detailinspectionService;
    }

    public void setDetailinspectionService(DetailinspectionFacade detailinspectionService) {
        this.detailinspectionService = detailinspectionService;
    }

    private void mettreAJourETATDetailInspection() {
        
        List<Detailinspection> lis= getDetailinspectionService().findByIdInspectionCamion(selected.getIdsuspensioncamion().getIdinspectioncamion().getIdinspectioncamion());
        for (Detailinspection li : lis) {
            li.setEtatalerte(Boolean.TRUE);
            getDetailinspectionService().edit(li);
        }
    }
    
    public void exporteReportPDF(ActionEvent actionEvent) throws JRException, IOException {
        initExporte();      
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        /***********************************************************************
        * Pour afficher une boîte de dialogue pour enregistrer le fichier sous
        * le nom rapport.pdf
        **********************************************************************/
        response.addHeader("Content-disposition",
                       "attachment;filename=rapport.pdf");
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
        response.setContentType("application/pdf");   
        context.responseComplete();
        //ServletOutputStream servletOutputStream= response.getOutputStream();
        //JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);        
        //context.responseComplete();
                
        
    }
    
    public void initExporte() throws JRException {
        FacesContext context = FacesContext.getCurrentInstance();
        String jasperPath ="/resources/report/reportGarage.jasper";
        String cheminFileToReport= context.getExternalContext().getRealPath(jasperPath);
        JRBeanCollectionDataSource jRBeanCollectionDataSource= new JRBeanCollectionDataSource(items);

        Map parametre=initParametre();        
        jasperPrint =JasperFillManager.fillReport(cheminFileToReport,parametre,jRBeanCollectionDataSource);        
    }
    
    /**
     *  Ici on Passera tous les variable necessaire pour la creation du
     * rapport de la Garage
     * @return 
     */
    public HashMap initParametre(){
        HashMap maps= new HashMap();
        maps.put("var1", "je suis dans var1");
        maps.put("var2", "je suis mois contenu dans  var2");
        maps.put("reparations", items);        
        return  maps;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }
    
    
    
    

    @FacesConverter(forClass = Reparationcamion.class)
    public static class ReparationcamionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReparationcamionController controller = (ReparationcamionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reparationcamionController");
            return controller.getReparationcamion(getKey(value));
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
            if (object instanceof Reparationcamion) {
                Reparationcamion o = (Reparationcamion) object;
                return getStringKey(o.getIdreparationcamion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reparationcamion.class.getName()});
                return null;
            }
        }

    }

}
