package gb.spring.homework.model;

import gb.spring.homework.validator.ClassFieldName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductFilter {
    private final FilterType type;
    @ClassFieldName(targetClass = Product.class)
    private final String fieldName;
    @NotNull
    private final FilterOperation operation;
    @NotNull
    private final String value;
    private final ProductFilter next;
}
