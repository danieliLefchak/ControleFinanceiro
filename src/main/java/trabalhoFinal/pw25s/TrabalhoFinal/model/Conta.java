package trabalhoFinal.pw25s.TrabalhoFinal.model;

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
public class Conta {
	private long id;
	private String agencia;
	private String banco;
	private int numero;
	private TipoConta tipoConta;
	private long idUsuario;
}
