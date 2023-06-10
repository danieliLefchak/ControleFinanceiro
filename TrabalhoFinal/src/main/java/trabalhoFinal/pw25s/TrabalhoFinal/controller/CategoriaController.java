package trabalhoFinal.pw25s.TrabalhoFinal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trabalhoFinal.pw25s.TrabalhoFinal.dto.CategoriaDto;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Categoria;
import trabalhoFinal.pw25s.TrabalhoFinal.service.CategoriaService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.CrudService;

@RestController
@RequestMapping("categorias")
public class CategoriaController extends CrudController<Categoria, CategoriaDto, Long>{
	private static CategoriaService categoriaService;
	private static ModelMapper modelMapper;
	
	public CategoriaController(CategoriaService categoriaService, ModelMapper modelMapper) {
		super(Categoria.class, CategoriaDto.class);
		this.categoriaService = categoriaService;
		this.modelMapper = modelMapper;
	}
	
	@Override
	protected CrudService<Categoria, Long> getService() {
		return this.categoriaService;
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return this.modelMapper;
	}
}
