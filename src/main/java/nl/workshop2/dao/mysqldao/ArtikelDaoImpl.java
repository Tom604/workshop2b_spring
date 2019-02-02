package nl.workshop2.dao.mysqldao;

import nl.workshop2.dao.GenericDaoImpl;
import nl.workshop2.domain.Artikel;
import nl.workshop2.utility.BPEntityManager;

public class ArtikelDaoImpl extends GenericDaoImpl<Artikel> {

	public ArtikelDaoImpl() {
		this.entityClass = Artikel.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl geïmplementeerd worden.
	 */
	
	public void insert(Artikel artikel) {
		em.getTransaction().begin();
		em.persist(artikel);
		em.getTransaction().commit();
		BPEntityManager.close();
	}
	
	public Artikel select(String username) {
		return em.find(entityClass, username);
	}
	
	public void delete(String naam) {
		em.getTransaction().begin();
		em.remove(naam);
		em.getTransaction().commit();
		BPEntityManager.close();
	}
}
