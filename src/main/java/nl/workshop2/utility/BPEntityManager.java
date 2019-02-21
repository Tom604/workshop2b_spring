package nl.workshop2.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

@Component
public class BPEntityManager {
	
	/*
	Uitzoeken of ik hier "implements EntityManager" moet toevoegen. Polymorf is deze klasse dan een EntityManager en zal de Spring container
	deze klasse kunnen autowiren via de @Autowired op het "protected EntityManager em" dataveld in de GenericDaoImpl<T> klasse. NEE
	
	Andere optie is om een constructor te maken waarin em zijn waarde toegekend krijgt. Deze klasse moet dan wel EntityManager implementen
	denk ik. Op laatste: NEE
	*/

//	private static final EntityManagerFactory EMFACTORY = Persistence.createEntityManagerFactory("workshop2b_spring");
//	private static final EntityManager EM = EMFACTORY.createEntityManager();
	
	private final EntityManagerFactory EMFACTORY;
	private final EntityManager EM;
	
	public BPEntityManager() {
		EMFACTORY = Persistence.createEntityManagerFactory("workshop2b_spring");
		EM = EMFACTORY.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return EM;
	}
	
	public void close() {
		EM.close();
		EMFACTORY.close();
	}
}
