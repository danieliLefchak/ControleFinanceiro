package trabalhoFinal.pw25s.TrabalhoFinal.serviceImpl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import trabalhoFinal.pw25s.TrabalhoFinal.model.Conta;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.ContaRepository;
import trabalhoFinal.pw25s.TrabalhoFinal.service.ContaService;

@Service
public class ContaServiceImpl extends CrudServiceImpl<Conta, Long> implements ContaService{
	private static ContaRepository contaRepository;
	
	public ContaServiceImpl(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	@Override
	protected JpaRepository<Conta, Long> getRepository() {
		return contaRepository;
	}

	@Override
	public List<Conta> findAllByIdUsuario(User user) {
		return contaRepository.findAllByIdUsuario(user);
	}

	@Override
	public Conta findOneByIdAndIdUsuario(long id, User user) {
		return contaRepository.findByIdAndIdUsuario(id, user);
	}

	
}
