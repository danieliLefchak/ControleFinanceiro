package trabalhoFinal.pw25s.TrabalhoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conta {
	private long id;
	private String agencia;
	private String banco;
	private int numero;
	private TipoConta tipoConta;
	private long idUsuario;
}
