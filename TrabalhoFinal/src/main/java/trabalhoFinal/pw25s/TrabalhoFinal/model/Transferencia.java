package trabalhoFinal.pw25s.TrabalhoFinal.model;

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
public class Transferencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "conta_id1", referencedColumnName = "id")
	private Conta idConta1;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "conta_id2", referencedColumnName = "id")
	private Conta idConta2;
	@NotNull
	private float valor;
}
