package trabalhoFinal.pw25s.TrabalhoFinal.service;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Transferencia;

public interface TransferenciaService extends CrudService<Transferencia, Long> {
	void AtualizaSaldoTranferencia(Transferencia transferencia);
}
