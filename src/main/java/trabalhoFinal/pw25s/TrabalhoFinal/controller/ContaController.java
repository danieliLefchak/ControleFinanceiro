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

import trabalhoFinal.pw25s.TrabalhoFinal.dto.ContaDto;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.service.ContaService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.CrudService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.UserService;

@RestController
@RequestMapping("contas")
public class ContaController extends CrudController<Conta, ContaDto, Long>{
	private static ContaService contaService;
	
	private static UserService userService;
	
	private static ModelMapper modelMapper;
	
	public ContaController(ContaService contaService, ModelMapper modelMapper, UserService userService) {
		super(Conta.class, ContaDto.class);
		this.contaService = contaService;
		this.modelMapper = modelMapper;
		this.userService = userService;
	}
	
	@Override
	protected CrudService<Conta, Long> getService() {
		return this.contaService;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return this.modelMapper;
	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<ContaDto>> findAll(){
		List<ContaDto> entitys = new ArrayList<>();
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			
			entitys = contaService.findAllByIdUsuario(user).stream()
					.map(super::ConvertToDto).collect(Collectors.toList());
		}
		
		return ResponseEntity.ok(entitys);
	}
	
	@Override
	@GetMapping("{id}")
	public ResponseEntity<ContaDto> findOne(@PathVariable Long id){
		Conta conta = new Conta();
		
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			
			conta = contaService.findOneByIdAndIdUsuario(id, user);
		}
		
		if(conta != null) {
			return ResponseEntity.ok(ConvertToDto(conta));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
