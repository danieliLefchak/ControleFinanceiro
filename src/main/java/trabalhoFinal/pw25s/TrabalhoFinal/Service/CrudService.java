package trabalhoFinal.pw25s.TrabalhoFinal.service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {
	T save(T entity);
	
	List<T> findAll();
	
	T findOne(ID id);
	
	void delete(ID id);
}
