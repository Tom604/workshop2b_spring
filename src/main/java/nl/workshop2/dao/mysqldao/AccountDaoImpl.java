package nl.workshop2.dao.mysqldao;

import nl.workshop2.domain.Account;
import nl.workshop2.utility.BPEntityManager;

import javax.persistence.Query;

import nl.workshop2.dao.GenericDaoImpl;

public class AccountDaoImpl extends GenericDaoImpl<Account> {
	
	public AccountDaoImpl() {
		this.entityClass = Account.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl geïmplementeerd worden.
	 */
	
	public void insert(Account account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		BPEntityManager.close();
	}
	
	
	public Account select(String username) {
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username", entityClass);
		return (Account) query.setParameter("username", username).getSingleResult();
	}
}
