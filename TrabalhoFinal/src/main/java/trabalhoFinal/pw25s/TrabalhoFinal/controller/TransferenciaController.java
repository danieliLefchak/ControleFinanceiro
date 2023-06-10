package trabalhoFinal.pw25s.TrabalhoFinal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import trabalhoFinal.pw25s.TrabalhoFinal.dto.TransferenciaDto;
import trabalhoFinal.pw25s.TrabalhoFinal.model.Transferencia;
import trabalhoFinal.pw25s.TrabalhoFinal.service.TransferenciaService;

@RestController
@RequestMapping("trasnferencias")
public class TransferenciaController{
	private static TransferenciaService transferenciaService;
	
	private static ModelMapper modelmapper;
	
	public TransferenciaController(TransferenciaService transferenciaService, ModelMapper modelmapper) {
		this.transferenciaService = transferenciaService;
		this.modelmapper = modelmapper;
	}
	
	@PostMapping
	public void FacaTransferencia(@Valid @RequestBody TransferenciaDto transferenciaDto) {
		Transferencia Entity = modelmapper.map(transferenciaDto, Transferencia.class);
		
		transferenciaService.AtualizaSaldoTranferencia(Entity);
	}

}
