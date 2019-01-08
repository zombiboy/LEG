package com.sodigaz.gel.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Detailinspection.class)
public abstract class Detailinspection_ {

	public static volatile SingularAttribute<Detailinspection, Date> datedelaireparation;
	public static volatile SingularAttribute<Detailinspection, Inspectioncamion> idinspectioncamion;
	public static volatile SingularAttribute<Detailinspection, Boolean> etatalerte;
	public static volatile SingularAttribute<Detailinspection, Panne> idpanne;
	public static volatile SingularAttribute<Detailinspection, Integer> iddetailinspection;
	public static volatile SingularAttribute<Detailinspection, String> commentaire;

}

