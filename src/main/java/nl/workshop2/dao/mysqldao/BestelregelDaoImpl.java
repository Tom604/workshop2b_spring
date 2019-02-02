package nl.workshop2.dao.mysqldao;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import nl.workshop2.dao.GenericDaoImpl;
import nl.workshop2.domain.Bestelregel;
import nl.workshop2.utility.BPEntityManager;

public class BestelregelDaoImpl extends GenericDaoImpl<Bestelregel> {

	public BestelregelDaoImpl() {
		this.entityClass = Bestelregel.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl geïmplementeerd worden.
	 */
	
	public void insert(Bestelregel bestelregel) {
		em.getTransaction().begin();
		em.persist(bestelregel);
		em.getTransaction().commit();
		BPEntityManager.close();
	}
	
	// Query met een JPQL statement, werkt het zo met b.bestelling_id? Ik denk het wel.
	public ArrayList<Bestelregel> selectMultiple(Long bestellingId) {
		TypedQuery<Bestelregel> query = em.createQuery(
				"SELECT b FROM Bestelregel b WHERE b.bestelling.id = :id", entityClass);
		return (ArrayList<Bestelregel>) query.setParameter("id", bestellingId).getResultList();
	}
}
