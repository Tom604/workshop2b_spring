package nl.workshop2.dao.mysqldao;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import nl.workshop2.dao.GenericDaoImpl;
import nl.workshop2.domain.Bestelling;
import nl.workshop2.utility.BPEntityManager;

public class BestellingDaoImpl extends GenericDaoImpl<Bestelling> {

	public BestellingDaoImpl() {
		this.entityClass = Bestelling.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl geïmplementeerd worden.
	 */
	
	public Bestelling insert(Bestelling bestelling) {
		em.getTransaction().begin();
		em.persist(bestelling);
		em.getTransaction().commit();
		BPEntityManager.close();
		
		// Is dit handig? - nog bedenken
		return em.find(entityClass, bestelling);
	}
	
	// Query met een JPQL statement, werkt het zo met b.klant_id? Ik denk het wel.
	public ArrayList<Bestelling> selectMultiple(Long klantId) {
		TypedQuery<Bestelling> query = em.createQuery("SELECT b FROM Bestelling b WHERE b.klant.id = :id", entityClass);
		return (ArrayList<Bestelling>) query.setParameter("id", klantId).getResultList();
	}
}
