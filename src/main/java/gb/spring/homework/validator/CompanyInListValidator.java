package gb.spring.homework.validator;

import gb.spring.homework.model.Company;
import gb.spring.homework.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyInListValidator implements ConstraintValidator<CompanyInList, Company> {

    @Autowired
    private CompanyService service;

    @Override
    public boolean isValid(Company value, ConstraintValidatorContext context) {
        return service.getByName(value.getName()) != null;
    }
}
