/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.View;

/**
 *
 * @author issouf
 */
import com.sodigaz.gel.Entity.Camion;
import com.sodigaz.gel.Entity.Detailinspection;
import com.sodigaz.gel.Entity.Detailsuspension;
import com.sodigaz.gel.Entity.Inspectioncamion;
import com.sodigaz.gel.Entity.Panne;
import com.sodigaz.gel.Entity.Suspensioncamion;
import com.sodigaz.gel.Service.CamionFacade;
import com.sodigaz.gel.Service.UtilisateurFacade;
import com.sodigaz.gel.Service.DetailinspectionFacade;
import com.sodigaz.gel.Service.DetailsuspensionFacade;
import com.sodigaz.gel.Service.InspectioncamionFacade;
import com.sodigaz.gel.Service.PanneFacade;
import com.sodigaz.gel.Service.SuspensioncamionFacade;
import com.sodigaz.gel.View.util.JsfUtil.ETATSCAMION;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean
public class CheckListView {
    
    @EJB
    private com.sodigaz.gel.Service.DetailinspectionFacade detailinspectionService;
    @EJB
    private com.sodigaz.gel.Service.PanneFacade panneService;    
    @EJB
    private com.sodigaz.gel.Service.InspectioncamionFacade inspectioncamionService;
    @EJB
    private com.sodigaz.gel.Service.UtilisateurFacade utilisateurService;
    @EJB
    private com.sodigaz.gel.Service.SuspensioncamionFacade suspensioncamionService;
    @EJB
    private com.sodigaz.gel.Service.DetailsuspensionFacade detailsuspensionService;
    @EJB
    private com.sodigaz.gel.Service.CamionFacade camionService;
    
    private List<Panne> listeTousLesPannes;
    private List<Panne> listeTousLesPannesRefus;
    private Panne[] selectedPannesElectricite;
    private Panne[] selectedPannesVoiture;
    private Panne[] selectedPannesEssieux;
    private Panne[] selectedPannesCabine;
    private Panne[] selectedPannesDivers;
    private Panne[] selectedPannesDocumentation;
    private List<Detailinspection> listeSelectedDetailInspection;    
    private Inspectioncamion selectedInspectionCamion= new Inspectioncamion();    
    private Detailinspection selectedDetailInspection;
    private Suspensioncamion selectedSuspensionCamion;
    private Detailsuspension selectedDetailSuspension;
    private Boolean panneDetected=false;
    public  Boolean problemeMecaniqueGrave=false;
    private String lieudSuspension="SIEGE";
        
    
    public void save() {        
        
        initialiserListeTousLesPannes();
        PrimeFaces current = PrimeFaces.current();
        if(isProblemeMecaniqueGrave()){            
            System.out.println("MAUVAIS --- Lancer le modal de confirmation");
            initialiserListeTousLesPannesRefus();
            current.executeScript("PF('dlgOuiSuspension').show();");
        }else{            
            System.out.println("BON +++Lancer le modal de confirmation");
            current.executeScript("PF('dlgNonSuspension').show();");
        }
    }
    
    public void ouiPourSuspension() throws IOException {
        PrimeFaces current = PrimeFaces.current();
        System.out.println("Suspension en cours BON  ");
        System.out.println("RECUPERER"+lieudSuspension);
        persisterInspectionETDetailInspection();//ETAP1
        persisterSuspensionETDetailSuspension();//ETAP2 
        
        current.executeScript("PF('dlgOuiSuspension').hide();");        
        System.out.println("FIN DE SUSPENSION YA ");
        //REDIRECTION VERS PAGE DE SUSPENSION
         
        NavigationHandler nh= FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/suspensioncamion/index.xhtml?faces-redirect=true");
    }
    
    public void nonPourSuspension() {
        
        PrimeFaces current = PrimeFaces.current();
        System.out.println("ACCEPTATION en cours");    
        persisterInspectionETDetailInspection();// PERMETTRA DE PERSISTER SI BON
                 
        
        current.executeScript("PF('dlgNonSuspension').hide();");
        System.out.println("FIN DE LA SORTIE ");
        //REDIRECTION VERS LA PAGE DES INSPECTIONS
        NavigationHandler nh= FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/inspectioncamion/index.xhtml?faces-redirect=true");
        //String rootContext = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        //String urlSuspn="http://localhost:8080/SodiGazGel-1.0-SNAPSHOT/faces/suspensioncamion/List.xhtml";
        //FacesContext.getCurrentInstance().getExternalContext().redirect(urlSuspn);
        
    }
    
    private void persisterInspectionETDetailInspection() {
        
        
        Camion camion = camionService.find(selectedInspectionCamion.getIdcamion().getIdcamion());
        
        
        if(isProblemeMecaniqueGrave()){
            selectedInspectionCamion.setCommentaire("REFUS");
            camion.setEtatcamion(ETATSCAMION.SUSPENDU);
            camion.setEstsuspension(true);
        }
        else{
            selectedInspectionCamion.setCommentaire("ACCEPTER");   
            camion.setEtatcamion(ETATSCAMION.EN_SERVICE);
            camion.setEstsuspension(false);
        }                    
        camionService.edit(camion);
                 
        selectedInspectionCamion.setDatecontrolecamion(new Date());
        selectedInspectionCamion.setHeurecontrolecamion(new Time(new Date().getTime()));
        if(utilisateurService.getCurrentUser()!=null){
            selectedInspectionCamion.setIdutilisateur(utilisateurService.getCurrentUser());
            System.out.println("USER TROUVER AVEC SUCCES");
        }
        else{
            selectedInspectionCamion.setIdutilisateur(utilisateurService.findAll().get(0));
        }
        selectedInspectionCamion = getInspectioncamionService().add(selectedInspectionCamion);
        /**
         * persisteListePanneDetailInspection(selectedPannesElectricite,selectedInspectionCamion);
        persisteListePanneDetailInspection(selectedPannesVoiture,selectedInspectionCamion);
        persisteListePanneDetailInspection(selectedPannesEssieux,selectedInspectionCamion);
        persisteListePanneDetailInspection(selectedPannesCabine,selectedInspectionCamion);
        persisteListePanneDetailInspection(selectedPannesDivers,selectedInspectionCamion);
        persisteListePanneDetailInspection(selectedPannesDocumentation,selectedInspectionCamion);
         */
        
        
        
        persisteListePanneDetailInspection(listeTousLesPannes,selectedInspectionCamion);
        
                 
    }
    
    private void persisterSuspensionETDetailSuspension() {
        selectedSuspensionCamion=new Suspensioncamion();// Initialiser actu mais changera apres
        selectedSuspensionCamion.setDatesuspensioncamion(new Date());
        selectedSuspensionCamion.setHeuresuspensioncamion(new Time(new Date().getTime()));
        selectedSuspensionCamion.setLieususpensioncamion("SIEGE");
        selectedSuspensionCamion.setEnreparation(false);
        selectedSuspensionCamion.setIdinspectioncamion(selectedInspectionCamion);
        selectedSuspensionCamion=getSuspensioncamionService().add(selectedSuspensionCamion);
        /**
         * persisteListePanneDetailSuspension(selectedPannesElectricite,selectedSuspensionCamion);
        persisteListePanneDetailSuspension(selectedPannesVoiture,selectedSuspensionCamion);
        persisteListePanneDetailSuspension(selectedPannesEssieux,selectedSuspensionCamion);
        persisteListePanneDetailSuspension(selectedPannesCabine,selectedSuspensionCamion);
        persisteListePanneDetailSuspension(selectedPannesDivers,selectedSuspensionCamion);
        persisteListePanneDetailSuspension(selectedPannesDocumentation,selectedSuspensionCamion);
         */
                                
        
        persisteListePanneDetailSuspension(listeTousLesPannes,selectedSuspensionCamion);
        System.out.println("PANNES INSERER DANS SUSPENSION");
        
    }
       
    public Boolean isProblemeMecaniqueGrave(){
        
        if(isProblemeMecaniqueGrave(selectedPannesElectricite)){
            return true;
        }else if(isProblemeMecaniqueGrave(selectedPannesVoiture)){
            return true;
        } 
        else if(isProblemeMecaniqueGrave(selectedPannesEssieux)){
            return true;
        } else if(isProblemeMecaniqueGrave(selectedPannesCabine)){
            return true;
        } else if(isProblemeMecaniqueGrave(selectedPannesDivers)){
            return true;
        } 
        else if(isProblemeMecaniqueGrave(selectedPannesDocumentation)){
            return true;
        } 
        return false;
    }
    
    public void initialiserListeTousLesPannes(){    
        listeTousLesPannes= new ArrayList<>();
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesElectricite));
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesVoiture));
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesEssieux));
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesCabine));
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesDivers));
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesDocumentation));    
        System.out.println("TOUS LES PANNES == "+listeTousLesPannes.size());
        for (Panne listeTousLesPanne : listeTousLesPannes) {
            System.out.println(".."+listeTousLesPanne.getDesignationpanne());
        }
    }
    public void initialiserListeTousLesPannesRefus(){         
        listeTousLesPannesRefus= new ArrayList<>();
        for (Panne panne : listeTousLesPannes) {
            if(panne.getDosuspend()){
                listeTousLesPannesRefus.add(panne);
            }            
        }
    }
        
    public Boolean isProblemeMecaniqueGrave(Panne[] pannes){
        
        for (Panne selectedPanne : pannes) {
            if (selectedPanne.getDosuspend()) {
                panneDetected=Boolean.TRUE;                
                return true;
            }
        }
        return false;
    }
    
    /**
     * les deux paramatre permettre d'enregistrer une Liste de Panne
     * chaque Panne sera enregistre avec comme Inspection celui passe en Parametre
     * @param listePannes
     * @param isp 
     */
    private void persisteListePanneDetailInspection(Panne[] listePannes,Inspectioncamion isp) {        
        if(listePannes != null){                
            for (Panne selectedPanne : listePannes) {
                selectedDetailInspection=new Detailinspection(); // Initialiser forcement avant de continuer
                selectedDetailInspection.setIdpanne(selectedPanne);
                selectedDetailInspection.setIdinspectioncamion(isp);
                getDetailinspectionService().edit(selectedDetailInspection);
                                
            }                  
        }        
    }
    
    private void persisteListePanneDetailInspection(List<Panne> listePannes,Inspectioncamion isp) {  
        System.out.println("ENTRER DANS PERSISTE DETAIL");
        initialiserListeTousLesPannes();
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();       
        
                
        if(listeTousLesPannes != null){                
            System.out.println("LISTES PANNES DIFFERNET DE NULL");
            for (Panne selectedPanne : listeTousLesPannes) {
                c.setTime(currentDate);
                c.add(Calendar.DATE, selectedPanne.getDureedatebutoirjrs());//a changer apres
                Date currentDatePlus = c.getTime();
                selectedDetailInspection=new Detailinspection(); // Initialiser forcement avant de continuer
                selectedDetailInspection.setIdpanne(selectedPanne);
                selectedDetailInspection.setIdinspectioncamion(isp);
                selectedDetailInspection.setEtatalerte(false);
                selectedDetailInspection.setDatedelaireparation(currentDatePlus);
                getDetailinspectionService().edit(selectedDetailInspection);
                                
            }                  
        }        
    }
    private void persisteListePanneDetailSuspension(Panne[] pannes,Suspensioncamion susC) {        
        if(pannes != null){                
            for (Panne selectedPanne : pannes) {                
                selectedDetailSuspension=new Detailsuspension();
                selectedDetailSuspension.setIdpanne(selectedPanne);  
                selectedDetailSuspension.setIdsuspensioncamion(susC);
                
                getDetailsuspensionService().edit(selectedDetailSuspension);                                
            }                  
        }        
    }
    
    private void persisteListePanneDetailSuspension(List<Panne> pannes,Suspensioncamion susC) {        
        if(pannes!= null){                
            for (Panne selectedPanne : pannes) {                
                selectedDetailSuspension=new Detailsuspension();
                selectedDetailSuspension.setIdpanne(selectedPanne);  
                selectedDetailSuspension.setIdsuspensioncamion(susC);                
                getDetailsuspensionService().edit(selectedDetailSuspension);                                
            }                  
        }        
    }
    
    
    /**
     * GETTER AND SETTER
     * @return 
     */
    
    public DetailinspectionFacade getDetailinspectionService() {
        return detailinspectionService;
    }

    public void setDetailinspectionService(DetailinspectionFacade detailinspectionService) {
        this.detailinspectionService = detailinspectionService;
    }

    public PanneFacade getPanneService() {
        return panneService;
    }

    public void setPanneService(PanneFacade panneService) {
        this.panneService = panneService;
    }

    public InspectioncamionFacade getInspectioncamionService() {
        return inspectioncamionService;
    }

    public void setInspectioncamionService(InspectioncamionFacade inspectioncamionService) {
        this.inspectioncamionService = inspectioncamionService;
    }

  
    public UtilisateurFacade getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(UtilisateurFacade utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    
    

    public SuspensioncamionFacade getSuspensioncamionService() {
        return suspensioncamionService;
    }

    public void setSuspensioncamionService(SuspensioncamionFacade suspensioncamionService) {
        this.suspensioncamionService = suspensioncamionService;
    }

    public DetailsuspensionFacade getDetailsuspensionService() {
        return detailsuspensionService;
    }

    public void setDetailsuspensionService(DetailsuspensionFacade detailsuspensionService) {
        this.detailsuspensionService = detailsuspensionService;
    }

  
    public Panne[] getSelectedPannesElectricite() {
        return selectedPannesElectricite;
    }

    public void setSelectedPannesElectricite(Panne[] selectedPannesElectricite) {
        this.selectedPannesElectricite = selectedPannesElectricite;
    }

    public Panne[] getSelectedPannesVoiture() {
        return selectedPannesVoiture;
    }

    public void setSelectedPannesVoiture(Panne[] selectedPannesVoiture) {
        this.selectedPannesVoiture = selectedPannesVoiture;
    }

    public Panne[] getSelectedPannesEssieux() {
        return selectedPannesEssieux;
    }

    public void setSelectedPannesEssieux(Panne[] selectedPannesEssieux) {
        this.selectedPannesEssieux = selectedPannesEssieux;
    }

    public Panne[] getSelectedPannesCabine() {
        return selectedPannesCabine;
    }

    public void setSelectedPannesCabine(Panne[] selectedPannesCabine) {
        this.selectedPannesCabine = selectedPannesCabine;
    }

    public Panne[] getSelectedPannesDivers() {
        return selectedPannesDivers;
    }

    public void setSelectedPannesDivers(Panne[] selectedPannesDivers) {
        this.selectedPannesDivers = selectedPannesDivers;
    }

    public Panne[] getSelectedPannesDocumentation() {
        return selectedPannesDocumentation;
    }

    public void setSelectedPannesDocumentation(Panne[] selectedPannesDocumentation) {
        this.selectedPannesDocumentation = selectedPannesDocumentation;
    }

    public List<Detailinspection> getListeSelectedDetailInspection() {
        return listeSelectedDetailInspection;
    }

    public void setListeSelectedDetailInspection(List<Detailinspection> listeSelectedDetailInspection) {
        this.listeSelectedDetailInspection = listeSelectedDetailInspection;
    }

    public Inspectioncamion getSelectedInspectionCamion() {
        return selectedInspectionCamion;
    }

    public void setSelectedInspectionCamion(Inspectioncamion selectedInspectionCamion) {
        this.selectedInspectionCamion = selectedInspectionCamion;
    }

    public Detailinspection getSelectedDetailInspection() {
        return selectedDetailInspection;
    }

    public void setSelectedDetailInspection(Detailinspection selectedDetailInspection) {
        this.selectedDetailInspection = selectedDetailInspection;
    }

    public Suspensioncamion getSelectedSuspensionCamion() {
        return selectedSuspensionCamion;
    }

    public void setSelectedSuspensionCamion(Suspensioncamion selectedSuspensionCamion) {
        this.selectedSuspensionCamion = selectedSuspensionCamion;
    }

    public Detailsuspension getSelectedDetailSuspension() {
        return selectedDetailSuspension;
    }

    public void setSelectedDetailSuspension(Detailsuspension selectedDetailSuspension) {
        this.selectedDetailSuspension = selectedDetailSuspension;
    }

    public Boolean getPanneDetected() {
        return panneDetected;
    }

    public void setPanneDetected(Boolean panneDetected) {
        this.panneDetected = panneDetected;
    }

    public Boolean getProblemeMecaniqueGrave() {
        return problemeMecaniqueGrave;
    }

    public void setProblemeMecaniqueGrave(Boolean problemeMecaniqueGrave) {
        this.problemeMecaniqueGrave = problemeMecaniqueGrave;
    }

    public String getLieudSuspension() {
        return lieudSuspension;
    }

    public void setLieudSuspension(String lieudSuspension) {
        this.lieudSuspension = lieudSuspension;
    }

    public List<Panne> getListeTousLesPannes() {
        return listeTousLesPannes;
    }

    public void setListeTousLesPannes(List<Panne> listeTousLesPannes) {
        this.listeTousLesPannes = listeTousLesPannes;
    }

    public List<Panne> getListeTousLesPannesRefus() {
        return listeTousLesPannesRefus;
    }

    public void setListeTousLesPannesRefus(List<Panne> listeTousLesPannesRefus) {
        this.listeTousLesPannesRefus = listeTousLesPannesRefus;
    }

    public CamionFacade getCamionService() {
        return camionService;
    }

    public void setCamionService(CamionFacade camionService) {
        this.camionService = camionService;
    }

    
    
}
