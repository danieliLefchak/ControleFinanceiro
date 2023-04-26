package trabalhoFinal.pw25s.TrabalhoFinal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaDto {
	@NotNull
	private String categoria;
}
