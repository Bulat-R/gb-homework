package gb.spring.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Company not found")
public class CompanyNotFoundException extends RuntimeException {
}
