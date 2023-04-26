package trabalhoFinal.pw25s.TrabalhoFinal.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}
}
