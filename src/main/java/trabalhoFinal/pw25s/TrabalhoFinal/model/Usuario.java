package trabalhoFinal.pw25s.TrabalhoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	private long id;
	private String nome; 
	private String email;
	private String telefone;
}
