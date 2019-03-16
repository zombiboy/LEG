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
import com.sodigaz.gel.Entity.Reparationcamion;
import com.sodigaz.gel.Entity.Suspensioncamion;
import com.sodigaz.gel.Service.CamionFacade;
import com.sodigaz.gel.Service.UtilisateurFacade;
import com.sodigaz.gel.Service.DetailinspectionFacade;
import com.sodigaz.gel.Service.DetailsuspensionFacade;
import com.sodigaz.gel.Service.InspectioncamionFacade;
import com.sodigaz.gel.Service.PanneFacade;
import com.sodigaz.gel.Service.ReparationcamionFacade;
import com.sodigaz.gel.Service.SuspensioncamionFacade;
import com.sodigaz.gel.View.util.JsfUtil.ETATSCAMION;
import java.io.IOException;
import java.sql.Time;
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
public class CheckListReparationView {
    
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
    @EJB
    private com.sodigaz.gel.Service.ReparationcamionFacade reparationcamionService;
    
    private List<Panne> listeTousLesPannes;
    private List<Panne> listeTousLesPannesRefus;
    private Panne[] selectedPannesElectricite;
    private Panne[] selectedPannesEssieux;
    private Panne[] selectedPannesDocumentation;
    private Panne[] selectedPannesMoteur;
    private Panne[] selectedPannesTransmission;
    private Panne[] selectedPannesDirection;
    private Panne[] selectedPannesPneumatique;
    private Panne[] selectedPannesFreinage;
    private Panne[] selectedPannesSuspension;
    private Panne[] selectedPannesSoudure;
    
    private List<Detailinspection> listeSelectedDetailInspection;    
    private Inspectioncamion selectedInspectionCamion= new Inspectioncamion();    
    private Detailinspection selectedDetailInspection;
    private Suspensioncamion selectedSuspensionCamion;
    private Detailsuspension selectedDetailSuspension;
    private Reparationcamion selectedReparationCamion =new Reparationcamion();
    private Boolean panneDetected=false;
    public  Boolean problemeMecaniqueGrave=false;
    private String lieudSuspension="SIEGE";
        
    
    
    
    public void saveDirectReparation() {        
        
        initialiserListeTousLesPannes();
        PrimeFaces current = PrimeFaces.current();
        if(isProblemeMecaniqueGrave()){            
            System.out.println("MAUVAIS --- Lancer le modal de confirmation");
            initialiserListeTousLesPannesRefus();
            current.executeScript("PF('dlgOuiSuspension').show();");
        }
        else{            
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
        
         
        NavigationHandler nh= FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/reparationcamion/index.xhtml?faces-redirect=true");
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
       
        if(utilisateurService.getCurrentUser()!=null){
            selectedInspectionCamion.setIdutilisateur(utilisateurService.getCurrentUser());
            System.out.println("USER TROUVER AVEC SUCCES");
        }
        else{
            selectedInspectionCamion.setIdutilisateur(utilisateurService.findAll().get(0));
        }
        selectedInspectionCamion.setDatecontrolecamion(new Date());
        selectedInspectionCamion.setHeurecontrolecamion(new Time(new Date().getTime()));
        selectedInspectionCamion = getInspectioncamionService().add(selectedInspectionCamion);
        
        
        
        persisteListePanneDetailInspection(listeTousLesPannes,selectedInspectionCamion);
        
                 
    }
    
    private void persisterSuspensionETDetailSuspension() {
        selectedSuspensionCamion=new Suspensioncamion();// Initialiser actu mais changera apres
        selectedSuspensionCamion.setDatesuspensioncamion(new Date());
        selectedSuspensionCamion.setHeuresuspensioncamion(new Time(new Date().getTime()));
        selectedSuspensionCamion.setLieususpensioncamion("SIEGE");
        selectedSuspensionCamion.setEnreparation(true);
        selectedSuspensionCamion.setIdinspectioncamion(selectedInspectionCamion);
        selectedSuspensionCamion=getSuspensioncamionService().add(selectedSuspensionCamion);
        /**
         * Passons a la reparation du camion
         */
                                
        
        persisteListePanneDetailSuspension(listeTousLesPannes,selectedSuspensionCamion);
        selectedReparationCamion.setIdsuspensioncamion(selectedSuspensionCamion);
        selectedReparationCamion.setEstreparer(false);
        System.out.println("PANNES INSERER DANS SUSPENSION");
        reparationcamionService.edit(selectedReparationCamion);
        
        Camion camion = camionService.find(selectedInspectionCamion.getIdcamion().getIdcamion());
        camion.setEtatcamion(ETATSCAMION.EN_REPARATION);
        camionService.edit(camion);
        
    }
       
    public Boolean isProblemeMecaniqueGrave(){
        
        if(isProblemeMecaniqueGrave(listeTousLesPannes)){
            return true;
        }         
        return false;
    }
    
    public void initialiserListeTousLesPannes(){    
        listeTousLesPannes= new ArrayList<>();
        if(selectedPannesElectricite!=null);
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesElectricite));        
        if(selectedPannesEssieux!=null);
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesEssieux));        
        if(selectedPannesDocumentation!=null);
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesDocumentation));  
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesMoteur));
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesTransmission)); 
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesDirection)); 
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesPneumatique));  
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesFreinage));  
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesSuspension));
        listeTousLesPannes.addAll(Arrays.asList(selectedPannesSoudure));  
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
    
    public Boolean isProblemeMecaniqueGrave(List<Panne> pannesR){
        initialiserListeTousLesPannes();
        pannesR=listeTousLesPannes;
        for (Panne selectedPanne : pannesR) {
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

    
    public Panne[] getSelectedPannesEssieux() {
        return selectedPannesEssieux;
    }

    public void setSelectedPannesEssieux(Panne[] selectedPannesEssieux) {
        this.selectedPannesEssieux = selectedPannesEssieux;
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

    public ReparationcamionFacade getReparationcamionService() {
        return reparationcamionService;
    }

    public void setReparationcamionService(ReparationcamionFacade reparationcamionService) {
        this.reparationcamionService = reparationcamionService;
    }

    public Reparationcamion getSelectedReparationCamion() {
        return selectedReparationCamion;
    }

    public void setSelectedReparationCamion(Reparationcamion selectedReparationCamion) {
        this.selectedReparationCamion = selectedReparationCamion;
    }

    public Panne[] getSelectedPannesMoteur() {
        return selectedPannesMoteur;
    }

    public void setSelectedPannesMoteur(Panne[] selectedPannesMoteur) {
        this.selectedPannesMoteur = selectedPannesMoteur;
    }

    public Panne[] getSelectedPannesTransmission() {
        return selectedPannesTransmission;
    }

    public void setSelectedPannesTransmission(Panne[] selectedPannesTransmission) {
        this.selectedPannesTransmission = selectedPannesTransmission;
    }

    public Panne[] getSelectedPannesDirection() {
        return selectedPannesDirection;
    }

    public void setSelectedPannesDirection(Panne[] selectedPannesDirection) {
        this.selectedPannesDirection = selectedPannesDirection;
    }

    public Panne[] getSelectedPannesPneumatique() {
        return selectedPannesPneumatique;
    }

    public void setSelectedPannesPneumatique(Panne[] selectedPannesPneumatique) {
        this.selectedPannesPneumatique = selectedPannesPneumatique;
    }

    public Panne[] getSelectedPannesFreinage() {
        return selectedPannesFreinage;
    }

    public void setSelectedPannesFreinage(Panne[] selectedPannesFreinage) {
        this.selectedPannesFreinage = selectedPannesFreinage;
    }

    public Panne[] getSelectedPannesSuspension() {
        return selectedPannesSuspension;
    }

    public void setSelectedPannesSuspension(Panne[] selectedPannesSuspension) {
        this.selectedPannesSuspension = selectedPannesSuspension;
    }

    public Panne[] getSelectedPannesSoudure() {
        return selectedPannesSoudure;
    }

    public void setSelectedPannesSoudure(Panne[] selectedPannesSoudure) {
        this.selectedPannesSoudure = selectedPannesSoudure;
    }

    
    
    
    
}
