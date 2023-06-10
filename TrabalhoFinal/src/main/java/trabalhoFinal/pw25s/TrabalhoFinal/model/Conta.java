package trabalhoFinal.pw25s.TrabalhoFinal.model;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String agencia;
	@NotNull
	private String banco;
	@NotNull
	private int numero;
	@NotNull
	private float saldo;
	@NotNull
	private String tipoConta;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User idUsuario;
}
