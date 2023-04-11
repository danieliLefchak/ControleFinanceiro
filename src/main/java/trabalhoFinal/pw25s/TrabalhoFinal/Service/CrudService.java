package trabalhoFinal.pw25s.TrabalhoFinal.Service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {
	List<T> findAll();
	
	T findOne(ID id) ;
	
	T save(T entity);
	
	void delete(ID id);
}
