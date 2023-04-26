package trabalhoFinal.pw25s.TrabalhoFinal.serviceImpl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Movimentacao;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.MovimentacaoRepository;
import trabalhoFinal.pw25s.TrabalhoFinal.service.MovimentacaoService;

@Service
public class MovimentacaoServiceImpl extends CrudServiceImpl<Movimentacao, Long> implements MovimentacaoService{
	private static MovimentacaoRepository movimentacaoRepository;
	
	public MovimentacaoServiceImpl(MovimentacaoRepository movimentacaoRepository) {
		this.movimentacaoRepository = movimentacaoRepository;
	}
	
	@Override
	protected JpaRepository<Movimentacao, Long> getRepository() {
		return movimentacaoRepository;
	}

	@Override
	public List<Movimentacao> findAllByidConta(Conta conta) {
		return movimentacaoRepository.findAllByIdConta(conta);
	}

	@Override
	public Movimentacao findOneByIdAndIdConta(long id, Conta conta) {
		return movimentacaoRepository.findByIdAndIdConta(id, conta);
	}
}
