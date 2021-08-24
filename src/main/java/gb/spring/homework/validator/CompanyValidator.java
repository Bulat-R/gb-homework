package gb.spring.homework.validator;

import gb.spring.homework.model.Company;
import gb.spring.homework.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class CompanyValidator implements ConstraintValidator<CompanyInList, Company> {

    private final CompanyService service;

    @Override
    public boolean isValid(Company value, ConstraintValidatorContext context) {
        return service.getByName(value.getName()) != null;
    }
}
