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
public class Situacao {
	private long id;
	private String situacao;
	private float valor;
}
