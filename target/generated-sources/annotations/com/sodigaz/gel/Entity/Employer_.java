package com.sodigaz.gel.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employer.class)
public abstract class Employer_ {

	public static volatile SingularAttribute<Employer, Fonctionemployer> idfonctionemployer;
	public static volatile SingularAttribute<Employer, String> matricule;
	public static volatile SingularAttribute<Employer, String> telephone;
	public static volatile SingularAttribute<Employer, Integer> idemployer;
	public static volatile SingularAttribute<Employer, String> nom;
	public static volatile SingularAttribute<Employer, String> prenom;
	public static volatile ListAttribute<Employer, Utilisateur> utilisateurList;

}

