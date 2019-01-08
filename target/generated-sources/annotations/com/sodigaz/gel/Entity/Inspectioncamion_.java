package com.sodigaz.gel.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Inspectioncamion.class)
public abstract class Inspectioncamion_ {

	public static volatile SingularAttribute<Inspectioncamion, Camion> idcamion;
	public static volatile SingularAttribute<Inspectioncamion, Utilisateur> idutilisateur;
	public static volatile SingularAttribute<Inspectioncamion, Integer> compteurcamion;
	public static volatile SingularAttribute<Inspectioncamion, Integer> idinspectioncamion;
	public static volatile SingularAttribute<Inspectioncamion, Date> datecontrolecamion;
	public static volatile ListAttribute<Inspectioncamion, Detailinspection> detailinspectionList;
	public static volatile SingularAttribute<Inspectioncamion, Date> heurecontrolecamion;
	public static volatile SingularAttribute<Inspectioncamion, String> commentaire;
	public static volatile ListAttribute<Inspectioncamion, Suspensioncamion> suspensioncamionList;

}

