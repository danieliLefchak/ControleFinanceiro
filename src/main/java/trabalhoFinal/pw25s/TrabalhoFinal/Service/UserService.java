package trabalhoFinal.pw25s.TrabalhoFinal.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.UserRepository;

@Service
public class UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
	}
}
