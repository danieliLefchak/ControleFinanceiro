package trabalhoFinal.pw25s.TrabalhoFinal.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimentacao {
	private long id;
	private float valor;
	private LocalDate data;
	private String categoria;
	private String descrição;
	//private Situacao situacao;//pensar melhor nisso aqui
	private TipoMovimentacao tipoMovimentacao;
	private long idConta;
	private long idSituacao;
}
