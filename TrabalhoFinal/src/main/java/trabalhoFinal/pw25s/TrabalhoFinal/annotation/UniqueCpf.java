package trabalhoFinal.pw25s.TrabalhoFinal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import trabalhoFinal.pw25s.TrabalhoFinal.validacao.UniqueCpfValidador;

@Constraint(validatedBy = UniqueCpfValidador.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {
	String message() default "Esse CPF já existe!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
