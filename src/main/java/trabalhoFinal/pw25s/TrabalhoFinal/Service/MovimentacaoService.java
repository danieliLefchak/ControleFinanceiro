package trabalhoFinal.pw25s.TrabalhoFinal.service;

import java.util.List;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Movimentacao;

public interface MovimentacaoService extends CrudService<Movimentacao, Long>{
	List<Movimentacao> findAllByidConta(Conta id);
	Movimentacao findOneByIdAndIdConta(long id, Conta conta);
}
