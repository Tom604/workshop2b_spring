package nl.workshop2.dao.mysqldao;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import nl.workshop2.dao.GenericDaoImpl;
import nl.workshop2.domain.Klant;
import nl.workshop2.utility.BPEntityManager;

public class KlantDaoImpl extends GenericDaoImpl<Klant> {

	public KlantDaoImpl() {
		this.entityClass = Klant.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl geïmplementeerd worden.
	 */
	
	public Klant insert(Klant klant) {
		em.getTransaction().begin();
		em.persist(klant);
		em.getTransaction().commit();
		BPEntityManager.close();
		
		// Is dit handig? - nog bedenken
		return em.find(entityClass, klant);
	}
	
	// Query met een JPQL statement
	public ArrayList<Klant> selectMultiple(String achternaam) {
		TypedQuery<Klant> query = em.createQuery("SELECT k FROM Klant k WHERE k.achternaam = :achternaam", entityClass);
		return (ArrayList<Klant>) query.setParameter("achternaam", achternaam).getResultList();
	}
}
