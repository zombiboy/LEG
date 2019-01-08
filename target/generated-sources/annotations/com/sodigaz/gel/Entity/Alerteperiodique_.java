package com.sodigaz.gel.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Alerteperiodique.class)
public abstract class Alerteperiodique_ {

	public static volatile SingularAttribute<Alerteperiodique, Camion> idcamion;
	public static volatile SingularAttribute<Alerteperiodique, Integer> idalerte;
	public static volatile SingularAttribute<Alerteperiodique, Date> datedernieralerte;
	public static volatile SingularAttribute<Alerteperiodique, String> libellealerte;
	public static volatile SingularAttribute<Alerteperiodique, Date> echeancealerte;

}

