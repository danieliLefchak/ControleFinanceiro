package trabalhoFinal.pw25s.TrabalhoFinal.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private float valor;
	@NotNull
	private LocalDate data;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;
	@NotNull
	private String descricao;
	@NotNull
	private String situacao;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "conta_id", referencedColumnName = "id")
	private Conta idConta;
	@NotNull
	private String tipoMovimentacao;
}
