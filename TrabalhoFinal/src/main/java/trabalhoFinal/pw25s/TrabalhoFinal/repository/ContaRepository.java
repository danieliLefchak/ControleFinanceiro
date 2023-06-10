package trabalhoFinal.pw25s.TrabalhoFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	List<Conta> findAllByIdUsuario(User user);
	Conta findByIdUsuario(User user);
	Conta findByIdAndIdUsuario(long id, User user);
}
