package trabalhoFinal.pw25s.TrabalhoFinal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import trabalhoFinal.pw25s.TrabalhoFinal.dto.UserDto;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.service.UserService;
import trabalhoFinal.pw25s.TrabalhoFinal.utils.GenericResponse;


@RestController
@RequestMapping("users")
public class UserController {
private final UserService userService;
	
	private final ModelMapper modelMapper;
	
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	public GenericResponse createUser(@Valid @RequestBody UserDto userDto) {
		User userEntity = modelMapper.map(userDto, User.class);
		
		userService.save(userEntity);
		GenericResponse resp = new GenericResponse();
		resp.setMessage("Usuário salvo com sucesso!");
		
		return resp;
	}
}
