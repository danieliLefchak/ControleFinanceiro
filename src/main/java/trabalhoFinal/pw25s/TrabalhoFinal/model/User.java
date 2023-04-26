package trabalhoFinal.pw25s.TrabalhoFinal.model;

import java.util.Collection;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import trabalhoFinal.pw25s.TrabalhoFinal.annotation.UniqueCpf;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String username; 
	
	/*
	 * Pode conter letras de a até z min 1 max 64
	 * podendo ser minusculas ou maiusculas
	 * numeros de 0 a 9 essa primeira parte 
	 * deve conter um @ apos ela e apos esse
	 * @ podem conter letras ou numeros e apos
	 * essas letras deve conter um . e apos 
	 * esse . pode conter letras maiusculas ou
	 * minusculas que podem ter o minimo de 2
	 * caracteres e o maximo de 18
	 * */
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	private String telefone;
	
	@UniqueCpf
	private String cpf;
	
	@NotNull
	private String password;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
