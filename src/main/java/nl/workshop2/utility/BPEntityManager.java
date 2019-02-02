package nl.workshop2.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BPEntityManager {

	private static final EntityManagerFactory EMFACTORY = Persistence.createEntityManagerFactory("workshop2a_hibernate");
	private static final EntityManager EM = EMFACTORY.createEntityManager();
	
	public static EntityManager getEntityManager() {
		return EM;
	}
	
	public static void close() {
		EM.close();
		EMFACTORY.close();
	}
}
