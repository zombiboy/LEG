package com.sodigaz.gel.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livreur.class)
public abstract class Livreur_ {

	public static volatile SingularAttribute<Livreur, String> nomlivreur;
	public static volatile SingularAttribute<Livreur, String> prenomlivreur;
	public static volatile SingularAttribute<Livreur, String> tellivreur;
	public static volatile SingularAttribute<Livreur, Integer> idlivreur;
	public static volatile ListAttribute<Livreur, Camion> camionList;

}

