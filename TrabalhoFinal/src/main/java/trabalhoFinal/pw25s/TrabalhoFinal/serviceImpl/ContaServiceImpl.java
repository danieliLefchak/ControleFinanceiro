package trabalhoFinal.pw25s.TrabalhoFinal.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import trabalhoFinal.pw25s.TrabalhoFinal.dto.ContaDto;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.ContaRepository;
import trabalhoFinal.pw25s.TrabalhoFinal.service.ContaService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.UserService;

@Service
public class ContaServiceImpl extends CrudServiceImpl<Conta, Long> implements ContaService{
	private static ContaRepository contaRepository;
	private static UserService userService;
	
	public ContaServiceImpl(ContaRepository contaRepository, UserService userService) {
		this.contaRepository = contaRepository;
		this.userService = userService;
	}
	
	@Override
	protected JpaRepository<Conta, Long> getRepository() {
		return contaRepository;
	}

	@Override
	public List<Conta> findAllByIdUsuario(User user) {
		return contaRepository.findAllByIdUsuario(user);
	}
	
	@Override
	public Conta save(Conta entity) {
		Conta conta = new Conta();
		
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			conta = entity;
			conta.setIdUsuario(user);
		}
		
		return contaRepository.save(conta);
	}

	@Override
	public Conta findOneByIdAndIdUsuario(long id) {
		Conta conta = new Conta();
		
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			
			conta = contaRepository.findByIdAndIdUsuario(id, user);
		}
		
		return conta;
	}

	@Override
	public List<Conta> findAll(){
		List<Conta> entitys = new ArrayList<>();
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			
			entitys = findAllByIdUsuario(user);
		}
		
		return entitys;
	}

	@Override
	public float calculaValorTotalSaldo() {
		List<Conta> contas = findAll();
		float valTotal = 0;
		
		for (Conta conta : contas) {
			valTotal += conta.getSaldo();
		}
		
		return valTotal;
	}
}
