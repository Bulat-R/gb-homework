package gb.spring.homework.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
public class ClassFieldNameValidator implements ConstraintValidator<ClassFieldName, String> {
    private Class<?> targetClass;

    @Override
    public void initialize(ClassFieldName constraintAnnotation) {
        targetClass = constraintAnnotation.targetClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.debug("Start class field validator: class = {}, value = {}", targetClass.getSimpleName(), value);
        if (value == null) {
            return true;
        }
        List<String> fields = Arrays.stream(targetClass.getDeclaredFields())
                .map(f -> f.getName().toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
        boolean result = fields.contains(value.toLowerCase(Locale.ROOT));
        log.debug("Validation result = {}", result);
        return result;
    }
}
