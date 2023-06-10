package trabalhoFinal.pw25s.TrabalhoFinal.erros;

import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrosApi {
	private long timestamp = new Date().getTime();
	private int status;
	private String mensagem;
	private String url;
	private Map<String, String> validaErros;
	
	public ErrosApi(int status, String mensagem, String url, Map<String, String> validaErros) {
		this.status = status;
		this.mensagem = mensagem;
		this.url = url;
		this.validaErros = validaErros;
	}
	
	public ErrosApi(String mensagem, String path, int status) {
		this.status = status;
		this.mensagem = mensagem;
		this.url = path;
	}
}
