package trabalhoFinal.pw25s.TrabalhoFinal.dto;

import lombok.Data;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Categoria;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;

@Data
public class MovimentacaoDto {
	private long id;

	private float valor;

	private String data;

	private Categoria categoria;

	private String descricao;
	
	private String situacao;

	private Conta idConta;

	private String tipoMovimentacao;
}
