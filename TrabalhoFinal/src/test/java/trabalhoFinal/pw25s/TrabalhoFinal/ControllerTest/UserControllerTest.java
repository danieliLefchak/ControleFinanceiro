package trabalhoFinal.pw25s.TrabalhoFinal.ControllerTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import trabalhoFinal.pw25s.TrabalhoFinal.erros.ErrosApi;
import trabalhoFinal.pw25s.TrabalhoFinal.model.User;
import trabalhoFinal.pw25s.TrabalhoFinal.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {
	private static final String API = "/users";
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void cleanup() {
		userRepository.deleteAll();
		testRestTemplate.getRestTemplate().getInterceptors().clear();
	}
	
	/*
	 * Teste que irá verificar se o codigo 
	 * retornado ao criar um usuário é 200 
	 * se for 200 o teste irá passar caso 
	 * contrario o teste irá falhar 
	 * */
	@Test
	public void postUsuario_quandoValido_recebeOk() {
		User user = criaUsuario();
		
		ResponseEntity<Object> response = postSignup(user, Object.class);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	/*
	 * Teste que irá verificar se o usuario 
	 * foi salvo no banco de dados, caso o 
	 * usuario tenha sido salvo o numero de
	 * registros no banco deve ser igual a 1  
	 * se for 1 o teste irá passar caso 
	 * contrario o teste irá falhar 
	 * */
	@Test
	public void postUsuario_quandoValido_salvaNoBanco() {
		User user = criaUsuario();
		
		ResponseEntity<Object> response = postSignup(user, Object.class);
		Assertions.assertThat(userRepository.count()).isEqualTo(1);
	}
	
	/*
	 * Teste que irá verificar se o objeto
	 * usuario tem alguma informaçõa, caso 
	 * ele não tenha nada, o teste iria passar
	 * pois isso irá retornar um erro de
	 * MethodArgumentNotValidException
	 * pois a senha e nome estão sem nenhum 
	 * valor e o teste está aguardando uma 
	 * erro de exceção, caso não de um erro de
	 * exceção o teste iria falhar.
	 * */
	@Test
	public void postUsuario_quandoInvalido_recebeErrosApi() {
		User user = new User();
		
		ResponseEntity<ErrosApi> response = postSignup(user, ErrosApi.class);
		
		Assertions.assertThat(response.getBody().getUrl()).isEqualTo(API);
	}
	
	/*
	 * Faz a requisição do tipo post na url que
	 * foi passada por parametro passando também
	 * a entidade e o tipo/classe dela
	 * */
	@Test
	public <T> ResponseEntity<T> postSignup(Object request, Class<T> responseType){
		return testRestTemplate.postForEntity(API, request, responseType);
	}
	
	private User criaUsuario() {
		User user = new User();
		user.setUsername("test-user");
		user.setPassword("s3nha");
		user.setEmail("teste_1234@test.com");
		user.setTelefone("2055550125");
		user.setCpf("1111");
		
		return user;
	}
}

