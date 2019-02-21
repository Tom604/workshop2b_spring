package nl.workshop2.dao.mysqldao;

import nl.workshop2.domain.Account;
import nl.workshop2.utility.BPEntityManager;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.workshop2.dao.GenericDaoImpl;

@Component
public class AccountDaoImpl extends GenericDaoImpl<Account> {
	
	@Autowired
	public AccountDaoImpl(BPEntityManager em) {
		super(em);
		this.entityClass = Account.class;
	}
	
	/*
	 * Hier de methodes implementeren die specifiek zijn voor deze entity en die niet door
	 * GenericDaoImpl ge�mplementeerd worden.
	 */
	
	public void insert(Account account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public Account select(String username) {
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username", entityClass);
		return (Account) query.setParameter("username", username).getSingleResult();
	}
}
