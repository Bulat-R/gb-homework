package gb.spring.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Sort parameters not valid")
public class SortParamsNotValidException extends RuntimeException {
}
