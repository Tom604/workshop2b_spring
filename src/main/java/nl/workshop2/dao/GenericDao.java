package nl.workshop2.dao;

import java.util.ArrayList;

public interface GenericDao<T> {

	public T select(Long id);
	public ArrayList<T> selectMultiple();
	public void update(T instance);
    public void delete(Long id);
}
