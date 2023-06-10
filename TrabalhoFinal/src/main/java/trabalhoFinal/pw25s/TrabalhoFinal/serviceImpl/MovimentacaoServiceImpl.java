package trabalhoFinal.pw25s.TrabalhoFinal.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Movimentacao;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.MovimentacaoRepository;
import trabalhoFinal.pw25s.TrabalhoFinal.service.ContaService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.MovimentacaoService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.UserService;

@Service
public class MovimentacaoServiceImpl extends CrudServiceImpl<Movimentacao, Long> implements MovimentacaoService{
	private static MovimentacaoRepository movimentacaoRepository;
	private static ContaService contaService;
	private static UserService userService;
	
	public MovimentacaoServiceImpl(MovimentacaoRepository movimentacaoRepository, ContaService contaService, UserService userService) {
		this.movimentacaoRepository = movimentacaoRepository;
		this.contaService = contaService;
		this.userService = userService;
	}
	
	@Override
	protected JpaRepository<Movimentacao, Long> getRepository() {
		return movimentacaoRepository;
	}

	@Override
	public List<Movimentacao> findAllByidConta(Long id) {
		List<Movimentacao> entitys = new ArrayList<>();
		Conta conta = new Conta();
		User user = new User();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
		} else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
			
			conta = contaService.findOneByIdAndIdUsuario(id);
			System.out.println("CONTA NO FIND ALL " + conta);
			
			entitys = movimentacaoRepository.findAllByIdConta(conta);
		}
		
		return entitys;
	}

	@Override
	public Movimentacao findOneByIdAndIdConta(Long id) {
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
			
			movimentacao = findOne(id);
			
			conta = contaService.findOneByIdAndIdUsuario(movimentacao.getIdConta().getId());
			
			movimentacao = movimentacaoRepository.findByIdAndIdConta(id, conta);
		}
		
		return movimentacao;
	}
	
	@Override
	public String AtualizaSaldoConformeTipoMovimentacao(Movimentacao movimentacao) {
		float valDesp = 0;
		Conta conta = contaService.findOne(movimentacao.getIdConta().getId());
		String str = "";
		
		if(movimentacao.getTipoMovimentacao().equals("Despesa") && movimentacao.getSituacao().equals("Pendente")) {
			if(movimentacao.getValor()<=conta.getSaldo()) {
				valDesp = conta.getSaldo() - movimentacao.getValor();
				movimentacao.setSituacao("Pago");
				str = "O valor foi debitado com sucesso";
			} else {
				str = "Saldo menor que o valor a ser debitado";
			}
		} else if(movimentacao.getTipoMovimentacao().equals("Receita") && movimentacao.getSituacao().equals("Pendente")) {
			valDesp = conta.getSaldo() + movimentacao.getValor();
			movimentacao.setSituacao("Recebido");
			str = "O valor foi creditado com sucesso";
		} 
		
		conta.setSaldo(valDesp);
		contaService.save(conta);
		return str;
	}
	
	@Override
	public List<Movimentacao> findCincoDespesas() {
		User user = new User();
		ArrayList<Movimentacao> movs = new ArrayList<Movimentacao>();
		ArrayList<Movimentacao> moviment = new ArrayList<Movimentacao>();
		List<Movimentacao> moviUltCinc = new ArrayList<Movimentacao>();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
		}
		
		List<Conta> contas = contaService.findAll();
		
		for(Conta conta: contas){
			movs = (ArrayList<Movimentacao>) movimentacaoRepository.findByOrderByDataAsc();
			System.out.println("\n\n\nDESP CONTAS ID :" + contas +"\n\n\n");
		}
		
		for (Movimentacao movimentacao : movs) {
			if(movimentacao.getIdConta().getIdUsuario().equals(user)) {
				if(movimentacao.getTipoMovimentacao().equals("Despesa")) {
					moviment.add(movimentacao);
				}
			}
		}
		
		moviUltCinc = moviment.subList(Math.max(moviment.size() - 5, 0), moviment.size());
		
		return moviUltCinc;
	}

	@Override
	public List<Movimentacao> findCincoReceitas() {
		User user = new User();
		ArrayList<Movimentacao> movs = new ArrayList<Movimentacao>();
		ArrayList<Movimentacao> moviment = new ArrayList<Movimentacao>();
		List<Movimentacao> moviUltCinc = new ArrayList<Movimentacao>();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			String nome = ((UserDetails)principal).getUsername();
			
		}else {
			String nome = principal.toString();
			
			user = userService.findByName(nome);
		}
		
		List<Conta> contas = contaService.findAll();
		
		for(Conta conta: contas){
			movs = (ArrayList<Movimentacao>) movimentacaoRepository.findByOrderByDataAsc();
		}
		
		for (Movimentacao movimentacao : movs) {
			if(movimentacao.getIdConta().getIdUsuario().equals(user)) {
				if(movimentacao.getTipoMovimentacao().equals("Receita")) {
					moviment.add(movimentacao);
				}
			}
		}

		moviUltCinc = moviment.subList(Math.max(moviment.size() - 5, 0), moviment.size());
		
		return moviUltCinc;
	}
}
