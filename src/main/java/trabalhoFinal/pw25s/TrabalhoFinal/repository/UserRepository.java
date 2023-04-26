package trabalhoFinal.pw25s.TrabalhoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalhoFinal.pw25s.TrabalhoFinal.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findUserByUsername(String username);
	User findUserByCpf(String cpf);
}
