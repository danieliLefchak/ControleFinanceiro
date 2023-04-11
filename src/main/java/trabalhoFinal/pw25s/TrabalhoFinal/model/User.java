package trabalhoFinal.pw25s.TrabalhoFinal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
	private long id;
	private String nome; 
	private String email;
	private String telefone;
	private String password;
}
