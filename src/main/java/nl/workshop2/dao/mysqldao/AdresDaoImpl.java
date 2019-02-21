package nl.workshop2.dao.mysqldao;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.workshop2.dao.GenericDaoImpl;
import nl.workshop2.domain.Adres;
import nl.workshop2.utility.BPEntityManager;

@Component
public class AdresDaoImpl extends GenericDaoImpl<Adres> {
	
	@Autowired
	public AdresDaoImpl(BPEntityManager em) {
		super(em);
		this.entityClass = Adres.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl ge�mplementeerd worden.
	 */
	
	public void insert(Adres adres) {
		em.getTransaction().begin();
		em.persist(adres);
		em.getTransaction().commit();
		em.close();
	}
	
	// Query met een JPQL statement, werkt het zo met a.klant.id? Ja, je moet het veld in de Klant klasse zelf aanspreken, niet de verwijzing in de Adres klasse. 
	public ArrayList<Adres> selectMultiple(Long klantId) {
		TypedQuery<Adres> query = em.createQuery("SELECT a FROM Adres a WHERE a.klant.id = :id", entityClass);
		return (ArrayList<Adres>) query.setParameter("id", klantId).getResultList();
	}
}
