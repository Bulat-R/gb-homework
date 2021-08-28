package gb.spring.homework.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ClassParameterValidator implements ConstraintValidator<ClassParameter, String[]> {
    private Class<?> targetClass;

    @Override
    public void initialize(ClassParameter constraintAnnotation) {
        targetClass = constraintAnnotation.targetClass();
    }

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        if (value == null || value.length == 0) {
            return true;
        }
        List<String> params = Arrays.stream(value)
                .map(p -> p.toLowerCase(Locale.ROOT))
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        if (params.size() != value.length) {
            return false;
        }
        List<String> fields = Arrays.stream(targetClass.getDeclaredFields())
                .map(f -> f.getName().toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
        return fields.containsAll(params);
    }
}
