package com.sodigaz.gel.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utilisateur.class)
public abstract class Utilisateur_ {

	public static volatile SingularAttribute<Utilisateur, Integer> idutilisateur;
	public static volatile SingularAttribute<Utilisateur, String> password;
	public static volatile SingularAttribute<Utilisateur, String> salt;
	public static volatile ListAttribute<Utilisateur, Inspectioncamion> inspectioncamionList;
	public static volatile SingularAttribute<Utilisateur, Date> lastlogin;
	public static volatile SingularAttribute<Utilisateur, String> rolename;
	public static volatile SingularAttribute<Utilisateur, Boolean> bloquer;
	public static volatile SingularAttribute<Utilisateur, Employer> idemployer;
	public static volatile SingularAttribute<Utilisateur, String> email;
	public static volatile SingularAttribute<Utilisateur, String> username;

}

