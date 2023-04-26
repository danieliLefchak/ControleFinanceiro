package trabalhoFinal.pw25s.TrabalhoFinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trabalhoFinal.pw25s.TrabalhoFinal.dto.MovimentacaoDto;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Movimentacao;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.service.ContaService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.CrudService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.MovimentacaoService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.UserService;
import trabalhoFinal.pw25s.TrabalhoFinal.utils.GenericResponse;

@RestController
@RequestMapping("movimentacoes")
public class MovimentacaoController extends CrudController<Movimentacao, MovimentacaoDto, Long> {
	private static MovimentacaoService movimentacaoService;
	
	private static ContaService contaService;
	
	private static ModelMapper modelmapper;
	
	private static UserService userService;
	
	public MovimentacaoController(MovimentacaoService movimentacaoService, ModelMapper modelmapper,
									ContaService contaService, UserService userService) {
		super(Movimentacao.class, MovimentacaoDto.class);
		this.movimentacaoService = movimentacaoService;
		this.modelmapper = modelmapper;
		this.contaService = contaService;
		this.userService = userService;
	}

	@Override
	protected CrudService<Movimentacao, Long> getService() {
		return movimentacaoService;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return modelmapper;
	}
	
	//mudar para o service
	public void AtualizaSaldoConformeTipoMovimentacao(Movimentacao movimentacao, long idConta) {
		float valDesp = 0;
		
		if(movimentacao.getTipoMovimentacao().equals("Despesa") && movimentacao.getSituacao().equals("Pendente")) {
			valDesp = movimentacao.getIdConta().getSaldo() - movimentacao.getValor();
			movimentacao.setSituacao("PAGO");
		} else if(movimentacao.getTipoMovimentacao().equals("Receita") && movimentacao.getSituacao().equals("Pendente")) {
			valDesp = movimentacao.getIdConta().getSaldo() + movimentacao.getValor();
			movimentacao.setSituacao("RECEBIDO");
		}
		
		Conta conta = contaService.findOne(movimentacao.getIdConta().getId());
		conta.setSaldo(valDesp);
		contaService.save(conta);
	}
	
	//mudar para o service
	@GetMapping("mov/{id}")
	public ResponseEntity calculaSaldo(@PathVariable long id){
		Movimentacao entity = getService().findOne(id);//acho que aqui seria movimentacaoService.findOne
		
		if(entity != null) {
			AtualizaSaldoConformeTipoMovimentacao(entity,entity.getIdConta().getId());
			return ResponseEntity.ok(new GenericResponse("Movimentação registrada"));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	//mudar para o service
	@Override
	@GetMapping
	public ResponseEntity<List<MovimentacaoDto>> findAll(){
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("findAll/{id}")
	public ResponseEntity<List<MovimentacaoDto>> findAllByContaId(@PathVariable Long id){
		List<MovimentacaoDto> entitys = new ArrayList<>();
		Conta conta = new Conta();
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			
			conta = contaService.findOneByIdAndIdUsuario(id, user);
			System.out.println("CONTA NO FIND ALL " + conta);
			
			entitys = movimentacaoService.findAllByidConta(conta).
					stream().map(super::ConvertToDto).collect(
							Collectors.toList());
			System.out.println("LISTA MOVIMENTAÇÕES " + entitys);
		}
		
		return ResponseEntity.ok(entitys);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<MovimentacaoDto> findOne(@PathVariable Long id){
		Movimentacao movimentacao = new Movimentacao();
		Conta conta = new Conta();
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			System.out.println("USER " + user);
			
			movimentacao = movimentacaoService.findOne(id);
			
			if(movimentacao == null) {
				return ResponseEntity.noContent().build();
			}
			
			conta = contaService.findOneByIdAndIdUsuario(movimentacao.getIdConta().getId(), user);
			
			movimentacao = movimentacaoService.findOneByIdAndIdConta(id, conta);
		}
		
		return ResponseEntity.ok(ConvertToDto(movimentacao));
	}
}
