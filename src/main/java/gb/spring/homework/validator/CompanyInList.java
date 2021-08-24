package gb.spring.homework.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CompanyValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CompanyInList {

    String message() default "Invalid company name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
