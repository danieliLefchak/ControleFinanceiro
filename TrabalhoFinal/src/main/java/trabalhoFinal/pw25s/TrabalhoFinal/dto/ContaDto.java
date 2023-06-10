package trabalhoFinal.pw25s.TrabalhoFinal.dto;

import lombok.Data;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;

@Data
public class ContaDto {
	private long id;

	private String agencia;

	private String banco;

	private int numero;

	private float saldo;

	private String tipoConta;

	private User idUsuario;
}
