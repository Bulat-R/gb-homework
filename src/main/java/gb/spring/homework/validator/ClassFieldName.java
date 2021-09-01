package gb.spring.homework.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClassFieldNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE_USE})
public @interface ClassFieldName {

    Class<?> targetClass();

    String message() default "Invalid parameter value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
