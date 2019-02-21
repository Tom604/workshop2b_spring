package nl.workshop2.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import nl.workshop2.utility.BPEntityManager;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	protected EntityManager em;
	protected Class<T> entityClass;
	
	@Autowired
	public GenericDaoImpl(BPEntityManager em) {
		this.em = em.getEntityManager();
	}

	@Override
	public T select(Long id) {
		return (T) em.find(entityClass, id);
	}

	// Query met een JPQL statement
	@Override
	public ArrayList<T> selectMultiple() {
		TypedQuery<T> query = em.createQuery("SELECT x FROM " + entityClass.getName() + " x", entityClass);
		return (ArrayList<T>) query.getResultList();
	}

	@Override
	public void update(T instance) {
		em.getTransaction().begin();
		em.merge(instance);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Long id) {
		em.getTransaction().begin();
		em.remove(id);
		em.getTransaction().commit();
		em.close();
	}
}
