package trabalhoFinal.pw25s.TrabalhoFinal.serviceImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Transferencia;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.TransferenciaRepository;
import trabalhoFinal.pw25s.TrabalhoFinal.service.ContaService;
import trabalhoFinal.pw25s.TrabalhoFinal.service.TransferenciaService;

@Service
public class TransferenciaServiceImpl extends CrudServiceImpl<Transferencia, Long> implements TransferenciaService {
	private static TransferenciaRepository transferenciaRepository;
	private static ContaService contaService;
	
	public TransferenciaServiceImpl(TransferenciaRepository transferenciaRepository, ContaService contaService) {
		this.transferenciaRepository = transferenciaRepository;
		this.contaService = contaService;
	}
	
	@Override
	public void AtualizaSaldoTranferencia(Transferencia transferencia) {
		float valDesp = 0;
		float valRec = 0;
		Conta cnt1 = contaService.findOne(transferencia.getIdConta1().getId());
		Conta cnt2 = contaService.findOne(transferencia.getIdConta2().getId());
		
		valRec = cnt1.getSaldo() + transferencia.getValor();
		valDesp = cnt2.getSaldo() - transferencia.getValor();
		
		cnt1.setSaldo(valRec);
		contaService.save(cnt1);
		cnt2.setSaldo(valDesp);
		contaService.save(cnt2);
	}

	@Override
	protected JpaRepository<Transferencia, Long> getRepository() {
		return this.transferenciaRepository;
	}

}
