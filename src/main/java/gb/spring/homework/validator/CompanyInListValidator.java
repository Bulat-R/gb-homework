package gb.spring.homework.validator;

import gb.spring.homework.model.Company;
import gb.spring.homework.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CompanyInListValidator implements ConstraintValidator<CompanyInList, Company> {

    @Autowired
    private CompanyService service;

    @Override
    public boolean isValid(Company value, ConstraintValidatorContext context) {
        log.debug("Start company name validator: {}", value);
        if (value.getName() == null) {
            return false;
        }
        boolean result = service.getByName(value.getName()) != null;
        log.debug("Validation result = {}", result);
        return result;
    }
}
