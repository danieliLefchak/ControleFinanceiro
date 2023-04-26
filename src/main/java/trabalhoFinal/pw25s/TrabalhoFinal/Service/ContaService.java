package trabalhoFinal.pw25s.TrabalhoFinal.service;

import java.util.List;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;

public interface ContaService extends CrudService<Conta, Long>{
	List<Conta> findAllByIdUsuario(User user);
	Conta findOneByIdAndIdUsuario(long id, User user);
}
