package trabalhoFinal.pw25s.TrabalhoFinal.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import trabalhoFinal.pw25s.TrabalhoFinal.annotation.UniqueCpf;

@Data
public class UserDto {
	private long id;
	
	private String username;
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	private String telefone;
	@UniqueCpf
	private String cpf;

	private String password;
}
