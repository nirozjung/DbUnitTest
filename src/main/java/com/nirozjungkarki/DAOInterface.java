package com.nirozjungkarki;
import java.util.List;

/**
 * @author nirozjungkarki
 *
 * @param <T>
 * @param <ID>
 */
public interface DAOInterface<T, ID> {

/**
 *  Allows the deletion of a tuple from the database
 *  
 *  @param Obj
 */
	public boolean delete(T obj);
	
/**
 * Retrieve all objects from a table	
 * @return
 */
	public List<T> findAll();
	
	public T getById(ID id);
	
	public T insert(T obj);
	
	public boolean update(T obj);
}
