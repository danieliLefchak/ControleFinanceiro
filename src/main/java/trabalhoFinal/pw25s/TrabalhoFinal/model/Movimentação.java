package trabalhoFinal.pw25s.TrabalhoFinal.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimentação {
	private long id;
	private float valor;
	private LocalDate data;
	private String categoria;
	private String descrição;
	private Situacao situacao;//pensar melhor nisso aqui
	private TipoMovimentacao tipoMovimentacao;
	private long idConta;
}
