package com.sodigaz.gel.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Suspensioncamion.class)
public abstract class Suspensioncamion_ {

	public static volatile SingularAttribute<Suspensioncamion, Inspectioncamion> idinspectioncamion;
	public static volatile ListAttribute<Suspensioncamion, Detailsuspension> detailsuspensionList;
	public static volatile SingularAttribute<Suspensioncamion, Boolean> enreparation;
	public static volatile ListAttribute<Suspensioncamion, Reparationcamion> reparationcamionList;
	public static volatile SingularAttribute<Suspensioncamion, Integer> idsuspensioncamion;
	public static volatile SingularAttribute<Suspensioncamion, Date> datesuspensioncamion;
	public static volatile SingularAttribute<Suspensioncamion, String> lieususpensioncamion;
	public static volatile SingularAttribute<Suspensioncamion, Date> heuresuspensioncamion;

}

