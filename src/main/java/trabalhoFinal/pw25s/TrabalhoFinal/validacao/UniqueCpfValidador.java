package trabalhoFinal.pw25s.TrabalhoFinal.validacao;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import trabalhoFinal.pw25s.TrabalhoFinal.annotation.UniqueCpf;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.UserRepository;

public class UniqueCpfValidador implements ConstraintValidator<UniqueCpf, String>{
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		
		if(userRepository.findUserByCpf(cpf) == null) {
			return true;
		}
		
		return false;
	}
	
	
}
