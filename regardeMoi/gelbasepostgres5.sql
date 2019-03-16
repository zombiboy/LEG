/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  24/11/2018 22:15:55                      */
/*==============================================================*/


drop index ASSOCIATION_15_FK;

drop index ALERTEPERIODIQUE_PK;

drop table AlertePeriodique;

drop index ASSOCIATION_12_FK2;

drop index ASSOCIATION_12_FK;

drop index ASSOCIATION_12_PK;

drop table Association_12;

drop index ASSOCIATION_16_FK;

drop index ASSOCIATION_14_FK;

drop index CAMION_PK;

drop table Camion;

drop index CIRCUITCAMION_PK;

drop table CircuitCamion;

drop index ASSOCIATION_7_FK;

drop index ASSOCIATION_6_FK;

drop index DETAILINSPECTION_PK;

drop table DetailInspection;

drop index ASSOCIATION_10_FK;

drop index ASSOCIATION_9_FK;

drop index DETAILSUSPENSION_PK;

drop table DetailSuspension;

drop index ASSOCIATION_17_FK;

drop index EMPLOYER_PK;

drop table Employer;

drop index FONCTIONEMPLOYER_PK;

drop table FonctionEmployer;

drop index ASSOCIATION_19_FK;

drop index ASSOCIATION_4_FK;

drop index INSPECTIONCAMION_PK;

drop table InspectionCamion;

drop index LIVREUR_PK;

drop table Livreur;

drop index PANNE_PK;

drop table Panne;

drop index ASSOCIATION_11_FK;

drop index REPARATIONCAMION_PK;

drop table ReparationCamion;

drop index ASSOCIATION_13_FK;

drop index SUSPENSIONCAMION_PK;

drop table SuspensionCamion;

drop index ASSOCIATION_18_FK;

drop index UTILISATEUR_PK;

drop table Utilisateur;

/*==============================================================*/
/* Table : AlertePeriodique                                     */
/*==============================================================*/
create table AlertePeriodique (
   idAlerte             SERIAL               not null,
   idCamion             INT4                 null,
   libelleAlerte        VARCHAR(254)         not null,
   dateDernierAlerte    DATE                 null,
   echeanceAlerte       DATE                 null,
   constraint PK_ALERTEPERIODIQUE primary key (idAlerte),
   constraint AK_IDALERTE_ALERTEPE unique (idAlerte)
);

/*==============================================================*/
/* Index : ALERTEPERIODIQUE_PK                                  */
/*==============================================================*/
create unique index ALERTEPERIODIQUE_PK on AlertePeriodique (
idAlerte
);

/*==============================================================*/
/* Index : ASSOCIATION_15_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_15_FK on AlertePeriodique (
idCamion
);

/*==============================================================*/
/* Table : Association_12                                       */
/*==============================================================*/
create table Association_12 (
   idReparationCamion   INT4                 not null,
   idPanne              INT4                 not null,
   constraint PK_ASSOCIATION_12 primary key (idReparationCamion, idPanne)
);

/*==============================================================*/
/* Index : ASSOCIATION_12_PK                                    */
/*==============================================================*/
create unique index ASSOCIATION_12_PK on Association_12 (
idReparationCamion,
idPanne
);

/*==============================================================*/
/* Index : ASSOCIATION_12_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_12_FK on Association_12 (
idReparationCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_12_FK2                                   */
/*==============================================================*/
create  index ASSOCIATION_12_FK2 on Association_12 (
idPanne
);

/*==============================================================*/
/* Table : Camion                                               */
/*==============================================================*/
create table Camion (
   idCamion             SERIAL               not null,
   idCuircuit           INT4                 not null,
   idLivreur            INT4                 null,
   immatriculation      VARCHAR(254)         not null,
   proprietaireCamion   VARCHAR(254)         null,
   compteurCamion       INT4                 null,
   estSuspension        BOOL                 null,
   etatCamion           VARCHAR(254)         null,
   codeCamion           VARCHAR(254)         null,
   constraint PK_CAMION primary key (idCamion)
);

/*==============================================================*/
/* Index : CAMION_PK                                            */
/*==============================================================*/
create unique index CAMION_PK on Camion (
idCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_14_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_14_FK on Camion (
idLivreur
);

/*==============================================================*/
/* Index : ASSOCIATION_16_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_16_FK on Camion (
idCuircuit
);

/*==============================================================*/
/* Table : CircuitCamion                                        */
/*==============================================================*/
create table CircuitCamion (
   idCuircuit           SERIAL               not null,
   libelleCircuit       VARCHAR(254)         not null,
   constraint PK_CIRCUITCAMION primary key (idCuircuit)
);

/*==============================================================*/
/* Index : CIRCUITCAMION_PK                                     */
/*==============================================================*/
create unique index CIRCUITCAMION_PK on CircuitCamion (
idCuircuit
);

/*==============================================================*/
/* Table : DetailInspection                                     */
/*==============================================================*/
create table DetailInspection (
   idDetailInspection   SERIAL               not null,
   idInspectionCamion   INT4                 not null,
   idPanne              INT4                 not null,
   dateDelaiReparation  DATE                 null,
   commentaire          VARCHAR(254)         null,
   etatAlerte           BOOL                 null,
   constraint PK_DETAILINSPECTION primary key (idDetailInspection),
   constraint AK_ID_DETAIL_INSPECTI_DETAILIN unique (idDetailInspection)
);

/*==============================================================*/
/* Index : DETAILINSPECTION_PK                                  */
/*==============================================================*/
create unique index DETAILINSPECTION_PK on DetailInspection (
idDetailInspection
);

/*==============================================================*/
/* Index : ASSOCIATION_6_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_6_FK on DetailInspection (
idInspectionCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_7_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_7_FK on DetailInspection (
idPanne
);

/*==============================================================*/
/* Table : DetailSuspension                                     */
/*==============================================================*/
create table DetailSuspension (
   idDetailSuspension   SERIAL               not null,
   idPanne              INT4                 not null,
   idSuspensionCamion   INT4                 not null,
   commentaire          VARCHAR(254)         null,
   constraint PK_DETAILSUSPENSION primary key (idDetailSuspension)
);

/*==============================================================*/
/* Index : DETAILSUSPENSION_PK                                  */
/*==============================================================*/
create unique index DETAILSUSPENSION_PK on DetailSuspension (
idDetailSuspension
);

/*==============================================================*/
/* Index : ASSOCIATION_9_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_9_FK on DetailSuspension (
idSuspensionCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_10_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_10_FK on DetailSuspension (
idPanne
);

/*==============================================================*/
/* Table : Employer                                             */
/*==============================================================*/
create table Employer (
   idEmployer           SERIAL               not null,
   idFonctionEmployer   INT4                 not null,
   nom                  VARCHAR(254)         null,
   prenom               VARCHAR(254)         null,
   telephone            VARCHAR(254)         null,
   matricule            VARCHAR(254)         null,
   constraint PK_EMPLOYER primary key (idEmployer)
);

/*==============================================================*/
/* Index : EMPLOYER_PK                                          */
/*==============================================================*/
create unique index EMPLOYER_PK on Employer (
idEmployer
);

/*==============================================================*/
/* Index : ASSOCIATION_17_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_17_FK on Employer (
idFonctionEmployer
);

/*==============================================================*/
/* Table : FonctionEmployer                                     */
/*==============================================================*/
create table FonctionEmployer (
   idFonctionEmployer   SERIAL               not null,
   nomEmploi            VARCHAR(254)         null,
   constraint PK_FONCTIONEMPLOYER primary key (idFonctionEmployer)
);

/*==============================================================*/
/* Index : FONCTIONEMPLOYER_PK                                  */
/*==============================================================*/
create unique index FONCTIONEMPLOYER_PK on FonctionEmployer (
idFonctionEmployer
);

/*==============================================================*/
/* Table : InspectionCamion                                     */
/*==============================================================*/
create table InspectionCamion (
   idInspectionCamion   SERIAL               not null,
   idUtilisateur        INT4                 not null,
   idCamion             INT4                 not null,
   dateControleCamion   DATE                 null,
   heureControleCamion  TIME                 null,
   compteurCamion       INT4                 null,
   commentaire          VARCHAR(254)         null,
   constraint PK_INSPECTIONCAMION primary key (idInspectionCamion)
);

/*==============================================================*/
/* Index : INSPECTIONCAMION_PK                                  */
/*==============================================================*/
create unique index INSPECTIONCAMION_PK on InspectionCamion (
idInspectionCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_4_FK                                     */
/*==============================================================*/
create  index ASSOCIATION_4_FK on InspectionCamion (
idCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_19_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_19_FK on InspectionCamion (
idUtilisateur
);

/*==============================================================*/
/* Table : Livreur                                              */
/*==============================================================*/
create table Livreur (
   idLivreur            SERIAL               not null,
   nomLivreur           VARCHAR(254)         not null,
   prenomLivreur        VARCHAR(254)         null,
   telLivreur           VARCHAR(254)         not null,
   constraint PK_LIVREUR primary key (idLivreur)
);

/*==============================================================*/
/* Index : LIVREUR_PK                                           */
/*==============================================================*/
create unique index LIVREUR_PK on Livreur (
idLivreur
);

/*==============================================================*/
/* Table : Panne                                                */
/*==============================================================*/
create table Panne (
   idPanne              SERIAL               not null,
   designationPanne     VARCHAR(254)         not null,
   doSuspend            BOOL                 null,
   typePanne            VARCHAR(254)         null,
   dureedateButoirJrs   INT4                 not null,
   constraint PK_PANNE primary key (idPanne),
   constraint AK_ID_PANNE_PANNE unique (idPanne)
);

/*==============================================================*/
/* Index : PANNE_PK                                             */
/*==============================================================*/
create unique index PANNE_PK on Panne (
idPanne
);

/*==============================================================*/
/* Table : ReparationCamion                                     */
/*==============================================================*/
create table ReparationCamion (
   idReparationCamion   SERIAL               not null,
   idSuspensionCamion   INT4                 not null,
   nomGarage            VARCHAR(254)         null,
   responsableGarage    VARCHAR(254)         null,
   telGarage            VARCHAR(254)         null,
   observationGarage    VARCHAR(254)         null,
   estReparer           BOOL                 null,
   dateEntreePrevueGarage DATE                 null,
   dateEntreeExacteGarage DATE                 null,
   dateSortiePrevueGarage DATE                 null,
   dateSortieExacteGarage DATE                 null,
   dateRemiseEnService  DATE                 null,
   etatReparation       VARCHAR(254)         null,
   dateReparationCamion DATE                 null,
   heureReparationCamion TIME                 null,
   constraint PK_REPARATIONCAMION primary key (idReparationCamion)
);

/*==============================================================*/
/* Index : REPARATIONCAMION_PK                                  */
/*==============================================================*/
create unique index REPARATIONCAMION_PK on ReparationCamion (
idReparationCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_11_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_11_FK on ReparationCamion (
idSuspensionCamion
);

/*==============================================================*/
/* Table : SuspensionCamion                                     */
/*==============================================================*/
create table SuspensionCamion (
   idSuspensionCamion   SERIAL               not null,
   idInspectionCamion   INT4                 null,
   dateSuspensionCamion DATE                 null,
   heureSuspensionCamion TIME                 null,
   lieuSuspensionCamion VARCHAR(254)         null,
   enReparation         BOOL                 null,
   constraint PK_SUSPENSIONCAMION primary key (idSuspensionCamion)
);

/*==============================================================*/
/* Index : SUSPENSIONCAMION_PK                                  */
/*==============================================================*/
create unique index SUSPENSIONCAMION_PK on SuspensionCamion (
idSuspensionCamion
);

/*==============================================================*/
/* Index : ASSOCIATION_13_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_13_FK on SuspensionCamion (
idInspectionCamion
);

/*==============================================================*/
/* Table : Utilisateur                                          */
/*==============================================================*/
create table Utilisateur (
   idUtilisateur        SERIAL               not null,
   idEmployer           INT4                 not null,
   username             VARCHAR(254)         null,
   password             VARCHAR(254)         null,
   email                VARCHAR(254)         null,
   bloquer              BOOL                 null,
   salt                 VARCHAR(254)         null,
   lastLogin            DATE                 null,
   roleName             VARCHAR(254)         null,
   constraint PK_UTILISATEUR primary key (idUtilisateur)
);

/*==============================================================*/
/* Index : UTILISATEUR_PK                                       */
/*==============================================================*/
create unique index UTILISATEUR_PK on Utilisateur (
idUtilisateur
);

/*==============================================================*/
/* Index : ASSOCIATION_18_FK                                    */
/*==============================================================*/
create  index ASSOCIATION_18_FK on Utilisateur (
idEmployer
);

alter table AlertePeriodique
   add constraint FK_ALERTEPE_ASSOCIATI_CAMION foreign key (idCamion)
      references Camion (idCamion)
      on delete restrict on update restrict;

alter table Association_12
   add constraint FK_ASSOCIAT_ASSOCIATI_PANNE foreign key (idPanne)
      references Panne (idPanne)
      on delete restrict on update restrict;

alter table Association_12
   add constraint FK_ASSOCIAT_ASSOCIATI_REPARATI foreign key (idReparationCamion)
      references ReparationCamion (idReparationCamion)
      on delete restrict on update restrict;

alter table Camion
   add constraint FK_CAMION_ASSOCIATI_LIVREUR foreign key (idLivreur)
      references Livreur (idLivreur)
      on delete restrict on update restrict;

alter table Camion
   add constraint FK_CAMION_ASSOCIATI_CIRCUITC foreign key (idCuircuit)
      references CircuitCamion (idCuircuit)
      on delete restrict on update restrict;

alter table DetailInspection
   add constraint FK_DETAILIN_ASSOCIATI_INSPECTI foreign key (idInspectionCamion)
      references InspectionCamion (idInspectionCamion)
      on delete restrict on update restrict;

alter table DetailInspection
   add constraint FK_DETAILIN_ASSOCIATI_PANNE foreign key (idPanne)
      references Panne (idPanne)
      on delete restrict on update restrict;

alter table DetailSuspension
   add constraint FK_DETAILSU_ASSOCIATI_PANNE foreign key (idPanne)
      references Panne (idPanne)
      on delete restrict on update restrict;

alter table DetailSuspension
   add constraint FK_DETAILSU_ASSOCIATI_SUSPENSI foreign key (idSuspensionCamion)
      references SuspensionCamion (idSuspensionCamion)
      on delete restrict on update restrict;

alter table Employer
   add constraint FK_EMPLOYER_ASSOCIATI_FONCTION foreign key (idFonctionEmployer)
      references FonctionEmployer (idFonctionEmployer)
      on delete restrict on update restrict;

alter table InspectionCamion
   add constraint FK_INSPECTI_ASSOCIATI_UTILISAT foreign key (idUtilisateur)
      references Utilisateur (idUtilisateur)
      on delete restrict on update restrict;

alter table InspectionCamion
   add constraint FK_INSPECTI_ASSOCIATI_CAMION foreign key (idCamion)
      references Camion (idCamion)
      on delete restrict on update restrict;

alter table ReparationCamion
   add constraint FK_REPARATI_ASSOCIATI_SUSPENSI foreign key (idSuspensionCamion)
      references SuspensionCamion (idSuspensionCamion)
      on delete restrict on update restrict;

alter table SuspensionCamion
   add constraint FK_SUSPENSI_ASSOCIATI_INSPECTI foreign key (idInspectionCamion)
      references InspectionCamion (idInspectionCamion)
      on delete restrict on update restrict;

alter table Utilisateur
   add constraint FK_UTILISAT_ASSOCIATI_EMPLOYER foreign key (idEmployer)
      references Employer (idEmployer)
      on delete restrict on update restrict;

