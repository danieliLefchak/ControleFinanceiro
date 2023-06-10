package trabalhoFinal.pw25s.TrabalhoFinal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;
import trabalhoFinal.pw25s.TrabalhoFinal.service.AuthService;

@EnableWebSecurity
@Configuration
public class WebSecurity {
	private final AuthService authService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
	
    public WebSecurity(AuthService authService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.authService = authService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }
    
    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http) {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(authService)
                .passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.build();

        http.csrf().disable()
        	.exceptionHandling()
        	.authenticationEntryPoint(authenticationEntryPoint).and()
        	.cors()
            .and()

            .authorizeRequests()
            .requestMatchers(HttpMethod.POST, "/users/**").permitAll()

            .requestMatchers("/h2-console/**").permitAll()
        	
        	.anyRequest().authenticated().and()
        	
        	.authenticationManager(authenticationManager)
        	.addFilter(new JWTAuthenticationFilter(authenticationManager, authService))
        	.addFilter(new JWTAuthorizationFilter(authenticationManager, authService))
        	
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        return http.build();

    }//requestMatchers
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
