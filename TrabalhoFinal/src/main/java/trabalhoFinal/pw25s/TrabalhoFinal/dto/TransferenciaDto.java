package trabalhoFinal.pw25s.TrabalhoFinal.dto;

import lombok.Data;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;

@Data
public class TransferenciaDto {
	private long id;
	
	private Conta idConta1;
	
	private Conta idConta2;
	
	private float valor;
}
