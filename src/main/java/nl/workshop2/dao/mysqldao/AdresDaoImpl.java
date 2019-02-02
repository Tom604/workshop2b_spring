package nl.workshop2.dao.mysqldao;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import nl.workshop2.dao.GenericDaoImpl;
import nl.workshop2.domain.Adres;
import nl.workshop2.utility.BPEntityManager;

public class AdresDaoImpl extends GenericDaoImpl<Adres> {

	public AdresDaoImpl() {
		this.entityClass = Adres.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl geïmplementeerd worden.
	 */
	
	public void insert(Adres adres) {
		em.getTransaction().begin();
		em.persist(adres);
		em.getTransaction().commit();
		BPEntityManager.close();
	}
	
	// Query met een JPQL statement, werkt het zo met a.klant_id? Ik denk het wel.
	public ArrayList<Adres> selectMultiple(Long klantId) {
		TypedQuery<Adres> query = em.createQuery("SELECT a FROM Adres a WHERE a.klant.id = :id", entityClass);
		return (ArrayList<Adres>) query.setParameter("id", klantId).getResultList();
	}
}
