package com.sodigaz.gel.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Camion.class)
public abstract class Camion_ {

	public static volatile SingularAttribute<Camion, Integer> idcamion;
	public static volatile SingularAttribute<Camion, Integer> compteurcamion;
	public static volatile SingularAttribute<Camion, Boolean> estsuspension;
	public static volatile SingularAttribute<Camion, String> etatcamion;
	public static volatile SingularAttribute<Camion, String> codecamion;
	public static volatile ListAttribute<Camion, Inspectioncamion> inspectioncamionList;
	public static volatile SingularAttribute<Camion, String> proprietairecamion;
	public static volatile SingularAttribute<Camion, Livreur> idlivreur;
	public static volatile SingularAttribute<Camion, Circuitcamion> idcuircuit;
	public static volatile ListAttribute<Camion, Alerteperiodique> alerteperiodiqueList;
	public static volatile SingularAttribute<Camion, String> immatriculation;

}

