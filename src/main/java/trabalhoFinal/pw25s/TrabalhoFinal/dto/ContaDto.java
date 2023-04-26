package trabalhoFinal.pw25s.TrabalhoFinal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import trabalhoFinal.pw25s.TrabalhoFinal.model.TipoConta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;

@Data
public class ContaDto {
	@NotNull
	private String agencia;
	@NotNull
	private String banco;
	@NotNull
	private int numero;
	@NotNull
	private float saldo;
	@NotNull
	private TipoConta tipoConta;
	@NotNull
	private User idUsuario;
}
