package trabalhoFinal.pw25s.TrabalhoFinal.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalhoFinal.pw25s.TrabalhoFinal.service.CrudService;

public abstract class CrudServiceImpl <T, ID extends Serializable> implements CrudService<T, ID> {
	protected abstract JpaRepository<T, ID> getRepository();
	
	@Override
	public T save(T entity) {
		return getRepository().save(entity);
	}
	
	@Override
	public List<T> findAll(){
		return getRepository().findAll();
	}
	
	@Override
	public T findOne(ID id) {
		return getRepository().findById(id).orElse(null);
	}
	
	@Override
	public void delete(ID id) {
		getRepository().deleteById(id);
	}
}
