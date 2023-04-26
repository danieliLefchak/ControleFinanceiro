package trabalhoFinal.pw25s.TrabalhoFinal.serviceImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Categoria;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.CategoriaRepository;
import trabalhoFinal.pw25s.TrabalhoFinal.service.CategoriaService;

@Service
public class CategoriaServiceImpl extends CrudServiceImpl<Categoria, Long> implements CategoriaService {
	private static CategoriaRepository categoriRepository;
	
	public CategoriaServiceImpl(CategoriaRepository categoriRepository) {
		this.categoriRepository = categoriRepository;
	}
	
	@Override
	protected JpaRepository<Categoria, Long> getRepository() {
		return categoriRepository;
	}

}
