package com.sodigaz.gel.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reparationcamion.class)
public abstract class Reparationcamion_ {

	public static volatile SingularAttribute<Reparationcamion, Boolean> estreparer;
	public static volatile ListAttribute<Reparationcamion, Panne> panneList;
	public static volatile SingularAttribute<Reparationcamion, String> telgarage;
	public static volatile SingularAttribute<Reparationcamion, Date> dateremiseenservice;
	public static volatile SingularAttribute<Reparationcamion, Date> datesortieprevuegarage;
	public static volatile SingularAttribute<Reparationcamion, Date> datesortieexactegarage;
	public static volatile SingularAttribute<Reparationcamion, Date> dateentreeexactegarage;
	public static volatile SingularAttribute<Reparationcamion, String> responsablegarage;
	public static volatile SingularAttribute<Reparationcamion, Date> dateentreeprevuegarage;
	public static volatile SingularAttribute<Reparationcamion, Date> datereparationcamion;
	public static volatile SingularAttribute<Reparationcamion, Integer> idreparationcamion;
	public static volatile SingularAttribute<Reparationcamion, String> nomgarage;
	public static volatile SingularAttribute<Reparationcamion, String> etatreparation;
	public static volatile SingularAttribute<Reparationcamion, Suspensioncamion> idsuspensioncamion;
	public static volatile SingularAttribute<Reparationcamion, String> observationgarage;
	public static volatile SingularAttribute<Reparationcamion, Date> heurereparationcamion;

}

