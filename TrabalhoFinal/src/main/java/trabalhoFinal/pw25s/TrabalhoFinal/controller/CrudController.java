package trabalhoFinal.pw25s.TrabalhoFinal.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import trabalhoFinal.pw25s.TrabalhoFinal.service.CrudService;
import trabalhoFinal.pw25s.TrabalhoFinal.utils.GenericResponse;

public abstract class CrudController<T, D, ID extends Serializable> {
	protected abstract CrudService<T, ID> getService();
	protected abstract ModelMapper getModelMapper();
	
	private final Class<T> typeClass;
	private final Class<D> typeDtoClass;
	
	public CrudController(Class<T> typeClass, Class<D> typeDtoClass) {
		this.typeClass = typeClass;
		this.typeDtoClass = typeDtoClass;
	}
	
	public D ConvertToDto(T entity) {
		return getModelMapper().map(entity, typeDtoClass);
	}
	
	public T ConvertToEntity(D entityDto) {
		return getModelMapper().map(entityDto, typeClass);
	}
	
	@PostMapping
	public ResponseEntity create(@RequestBody @Valid T entity) {
	    return ResponseEntity.status(HttpStatus.CREATED)
	    		.body(ConvertToDto(getService()
	    				.save(entity)));
	}
	
	@GetMapping
	public ResponseEntity<List<D>> findAll(){
		return ResponseEntity.ok(
				getService().findAll().stream()
				.map(this::ConvertToDto).collect(Collectors.toList())
		);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<D> findOne(@PathVariable ID id){
		T entity = getService().findOne(id);
		
		if(entity != null) {
			return ResponseEntity.ok(ConvertToDto(entity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping("{id}")
    public ResponseEntity updateRegister(@RequestBody @Valid T entity, @PathVariable ID id) {
		try {
			T ent = getService().findOne(id);
	    	
	    	if(ent != null) {
	    		getService().save(entity);
	    		return ResponseEntity.ok(new GenericResponse("Registro atualizado com sucesso"));
	    	}else {
	    		return ResponseEntity.noContent().build();
	    	}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new GenericResponse("Erro ao atualizar o registro!"));
		}
    }
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable ID id){
		getService().delete(id);
		return ResponseEntity.ok( new GenericResponse("Registro excluido"));
	}
}
