package trabalhoFinal.pw25s.TrabalhoFinal.service;

import java.util.List;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Movimentacao;

public interface MovimentacaoService extends CrudService<Movimentacao, Long>{
	List<Movimentacao> findAllByidConta(Long id);
	Movimentacao findOneByIdAndIdConta(Long id);
	String AtualizaSaldoConformeTipoMovimentacao(Movimentacao movimentacao);
	List<Movimentacao> findCincoDespesas();
	List<Movimentacao> findCincoReceitas();
}
