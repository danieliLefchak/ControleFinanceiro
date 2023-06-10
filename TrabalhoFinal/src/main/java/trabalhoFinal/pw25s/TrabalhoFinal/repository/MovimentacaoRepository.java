package trabalhoFinal.pw25s.TrabalhoFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
	List<Movimentacao> findAllByIdConta(Conta conta);
	Movimentacao findByIdAndIdConta(long id, Conta conta);
	List<Movimentacao> findByOrderByDataAsc();
}
