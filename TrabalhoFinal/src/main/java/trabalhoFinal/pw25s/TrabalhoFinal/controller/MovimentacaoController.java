package trabalhoFinal.pw25s.TrabalhoFinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import trabalhoFinal.pw25s.TrabalhoFinal.dto.MovimentacaoDto;
import trabalhoFinal.pw25s.TrabalhoFinal.dto.TransferenciaDto;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Movimentacao;
import trabalhoFinal.pw25s.TrabalhoFinal.service.CrudService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.MovimentacaoService;
import trabalhoFinal.pw25s.TrabalhoFinal.utils.GenericResponse;

@RestController
@RequestMapping("movimentacoes")
public class MovimentacaoController extends CrudController<Movimentacao, MovimentacaoDto, Long> {
	private static MovimentacaoService movimentacaoService;
	
	private static ModelMapper modelmapper;
	
	public MovimentacaoController(MovimentacaoService movimentacaoService, ModelMapper modelmapper) {
		super(Movimentacao.class, MovimentacaoDto.class);
		this.movimentacaoService = movimentacaoService;
		this.modelmapper = modelmapper;
	}

	@Override
	protected CrudService<Movimentacao, Long> getService() {
		return movimentacaoService;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return modelmapper;
	}
	
	@GetMapping("mov/{id}")
	public ResponseEntity calculaSaldo(@PathVariable long id){
		Movimentacao entity = getService().findOne(id);
		String str = "";
		
		if(entity != null) {
			str = movimentacaoService.AtualizaSaldoConformeTipoMovimentacao(entity);
			return ResponseEntity.ok(new GenericResponse(str));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<MovimentacaoDto>> findAll(){
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("findAll/{id}")
	public ResponseEntity<List<MovimentacaoDto>> findAllByContaId(@PathVariable Long id){
		List<MovimentacaoDto> entitys = new ArrayList<>();
			
			entitys = movimentacaoService.findAllByidConta(id).
					stream().map(super::ConvertToDto).collect(
							Collectors.toList());
		
		return ResponseEntity.ok(entitys);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<MovimentacaoDto> findOne(@PathVariable Long id){
		Movimentacao movimentacao = new Movimentacao();
			
		movimentacao = movimentacaoService.findOneByIdAndIdConta(id);
		
		return ResponseEntity.ok(ConvertToDto(movimentacao));
	}
	
	@GetMapping("desp")
	public List<Movimentacao> findCincoDespesas(){
		return movimentacaoService.findCincoDespesas();
	}
	
	@GetMapping("rec")
	public List<Movimentacao> findCincoReceitas(){
		return movimentacaoService.findCincoReceitas();
	}
}
