package com.sodigaz.gel.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Panne.class)
public abstract class Panne_ {

	public static volatile SingularAttribute<Panne, String> typepanne;
	public static volatile ListAttribute<Panne, Detailsuspension> detailsuspensionList;
	public static volatile SingularAttribute<Panne, Integer> idpanne;
	public static volatile ListAttribute<Panne, Detailinspection> detailinspectionList;
	public static volatile ListAttribute<Panne, Reparationcamion> reparationcamionList;
	public static volatile SingularAttribute<Panne, String> designationpanne;
	public static volatile SingularAttribute<Panne, Integer> dureedatebutoirjrs;
	public static volatile SingularAttribute<Panne, Boolean> dosuspend;

}

