package trabalhoFinal.pw25s.TrabalhoFinal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Categoria;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;

@Data
public class MovimentacaoDto {
	@NotNull
	private float valor;
	@NotNull
	private String data;
	@NotNull
	private Categoria categoria;
	@NotNull
	private String descricao;
	@NotNull
	private String situacao;
	@NotNull
	private Conta idConta;
	@NotNull
	private String tipoMovimentacao;
}
